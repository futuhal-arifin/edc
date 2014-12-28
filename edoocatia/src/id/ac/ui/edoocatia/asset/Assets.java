package id.ac.ui.edoocatia.asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

public class Assets implements Disposable, AssetErrorListener {

	private static final String TAG = "Assets";
	private Logger logger;
	private AssetManager manager;
	private ObjectMap<String, Array<Asset>> groups;

	public Assets(String assetFile) {
		logger = new Logger(TAG, Logger.INFO);

		manager = new AssetManager();
		manager.setErrorListener(this);

		loadGroups(assetFile);
	}

	public void loadGroup(String groupName) {
		logger.info("loading group " + groupName);

		Array<Asset> assets = groups.get(groupName, null);

		if (assets != null) {
			for (Asset asset : assets) {
				manager.load(asset.path, asset.type);
			}
		} else {
			logger.error("error loading group " + groupName + ", not found");
		}
	}

	public void unloadGroup(String groupName) {
		logger.info("unloading group " + groupName);

		Array<Asset> assets = groups.get(groupName, null);

		if (assets != null) {
			for (Asset asset : assets) {
				if (manager.isLoaded(asset.path, asset.type)) {
					manager.unload(asset.path);
				}
			}
		} else {
			logger.error("error unloading group " + groupName + ", not found");
		}
	}

	public void unloadAllGroups() {
		manager.clear();
	}

	public synchronized <T> T get(String fileName) {
		return manager.get(fileName);
	}

	public synchronized <T> T get(String fileName, Class<T> type) {
		return manager.get(fileName, type);
	}

	public boolean update() {
		return manager.update();
	}

	public void finishLoading() {
		manager.finishLoading();
	}

	public float getProgress() {
		return manager.getProgress();
	}

	@Override
	public void dispose() {
		logger.info("shutting down");
		manager.dispose();
	}

	public void error(String fileName, Class<?> type, Throwable throwable) {
		logger.error("error loading " + fileName);
	}

	private void loadGroups(String assetFile) {
		groups = new ObjectMap<String, Array<Asset>>();

		logger.info("loading file " + assetFile);

		try {
			XmlReader reader = new XmlReader();
			Element root = reader.parse(Gdx.files.internal(assetFile));

			for (Element groupElement : root.getChildrenByName("group")) {
				String groupName = groupElement.getAttribute("name");

				if (groups.containsKey(groupName)) {
					logger.error("group " + groupName
							+ " already exists, skipping");
					continue;
				}

				logger.info("registering group " + groupName);

				Array<Asset> assets = new Array<Asset>();

				for (Element assetElement : groupElement
						.getChildrenByName("asset")) {
					assets.add(new Asset(assetElement.getAttribute("name", ""),
							assetElement.getAttribute("type", ""), assetElement
									.getAttribute("path", "")));
				}

				groups.put(groupName, assets);
			}
		} catch (Exception e) {
			logger.error("error loading file " + assetFile + " "
					+ e.getMessage());
		}
	}

	private class Asset {
		public Class<?> type;
		public String path;

		public Asset(String name, String type, String path) {
			try {
				this.type = Class.forName(type);
				this.path = path;
			} catch (ClassNotFoundException e) {
				logger.error("asset type " + type + " not found");
			}
		}
	}

	@Override
	public void error(@SuppressWarnings("rawtypes") AssetDescriptor asset,
			Throwable throwable) {
		logger.error("error loading asset");
	}

}