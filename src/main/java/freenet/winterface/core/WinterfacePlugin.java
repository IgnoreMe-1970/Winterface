package freenet.winterface.core;

import java.net.URL;

import org.apache.log4j.Logger;

import freenet.node.Node;
import freenet.pluginmanager.FredPlugin;
import freenet.pluginmanager.FredPluginThreadless;
import freenet.pluginmanager.FredPluginVersioned;
import freenet.pluginmanager.PluginRespirator;

/**
 * Winterface {@link FredPlugin}
 * <p>
 * Replaces FProxy with Apache Wicket and Jetty web server.
 * </p>
 * 
 * @author pasub
 * 
 */
public class WinterfacePlugin implements FredPlugin, FredPluginThreadless, FredPluginVersioned {

	/**
	 * Log4j logger
	 */
	static final Logger logger = Logger.getLogger(WinterfacePlugin.class);

	/**
	 * {@link URL} at which {@link WinterfacePlugin} resides
	 */
	private URL plugin_path;

	/**
	 * True if in development mode. Change to {@code false} to switch to
	 * deployment mode
	 */
	private final static boolean DEV_MODE = false;
	
	@Override
	public void runPlugin(PluginRespirator pr) {
		// Load path
		plugin_path = this.getClass().getClassLoader().getResource(".");
		// Register logger and so on
		logger.debug("Loaded WinterFacePlugin on path " + plugin_path);
		// initServer();
		ServerWrapper.startServer(DEV_MODE);
	}

	@Override
	public void terminate() {
		ServerWrapper.terminateServer();
	}

	@Override
	public String getVersion() {
		// FIXME do something :P
		return null;
	}

	/**
	 * Returns {@code true} if in development mode.
	 * 
	 * @return {@code false} if in deployment mode
	 */
	public static boolean inDevMode() {
		return DEV_MODE;
	}

	/**
	 * Just for test cases if {@link Node} is not needed
	 * 
	 * @param args
	 *            start arguments
	 */
	public static void main(String[] args) {
		WinterfacePlugin p = new WinterfacePlugin();
		p.runPlugin(null);
	}

}