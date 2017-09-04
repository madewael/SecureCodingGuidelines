# MUTABLE-11: Do not expose mutable statics
Private statics are easily exposed through public interfaces, if sometimes only in a limited way (see Guidelines 6-2 and 6-6). Mutable statics may also change behaviour between unrelated code. To ensure safe code, private statics should be treated as if they are public. Adding boilerplate to expose statics as singletons does not fix these issues.

Mutable statics may be used as caches of immutable flyweight values. Mutable objects should never be cached in statics. Even instance pooling of mutable objects should be treated with extreme caution.

Some mutable statics require a security permission to update state. The updated value will be visible globally. Therefore mutation should be done with extreme care. Methods that update global state or provide a capability to do so, with a security check, include:

        java.lang.ClassLoader.getSystemClassLoader 
        java.lang.System.clearProperty
        java.lang.System.getProperties 
        java.lang.System.setErr 
        java.lang.System.setIn 
        java.lang.System.setOut 
        java.lang.System.setProperties 
        java.lang.System.setProperty 
        java.lang.System.setSecurityManager 
        java.net.Authenticator.setDefault 
        java.net.CookieHandler.getDefault 
        java.net.CookieHandler.setDefault 
        java.net.Datagram.setDatagramSocketImplFactory 
        java.net.HttpURLConnection.setFollowRedirects 
        java.net.ProxySelector.setDefaul 
        java.net.ResponseCache.getDefault 
        java.net.ResponseCache.setDefault 
        java.net.ServerSocket.setSocketFactory 
        java.net.Socket.setSocketImplFactory
        java.net.URL.setURLStreamHandlerFactory 
        java.net.URLConnection.setContentHandlerFactory 
        java.net.URLConnection.setFileNameMap 
        java.rmi.server.RMISocketFactory.setFailureHandler 
        java.rmi.server.RMISocketFactory.setSocketFactory 
        java.rmi,activation.ActivationGroup.createGroup 
        java.rmi,activation.ActivationGroup.setSystem 
        java.rmi.server.RMIClassLoader.getDefaultProviderInstance 
        java.secuirty.Policy.setPolicy 
        java.sql.DriverManager.setLogStream 
        java.sql.DriverManager.setLogWriter 
        java.util.Locale.setDefault 
        java.util.TimeZone.setDefault 
        javax.naming.spi.NamingManager.setInitialContextFactoryBuilder 
        javax.naming.spi.NamingManager.setObjectFactoryBuilder 
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier 
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory 
        javax.net.ssl.SSLContext.setDefault 
        javax.security.auth.login.Configuration.setConfiguration 
        javax.security.auth.login.Policy.setPolicy 

Java PlugIn and Java WebStart isolate certain global state within an "AppContext". Often no security permissions are necessary to access this state, so it cannot be trusted (other than for Same Origin Policy within PlugIn and WebStart). While there are security checks, the state is still intended to remain within the context. Objects retrieved directly or indirectly from the AppContext should therefore not be stored in other variations of globals, such as plain statics of classes in a shared class loader. Any library code directly or indirectly using AppContext on behalf of an application should be clearly documented. Users of AppContext include:

        Extensively within AWT
        Extensively within Swing
        Extensively within JavaBeans Long Term Persistence
        java.beans.Beans.setDesignTime
        java.beans.Beans.setGuiAvailable 
        java.beans.Introspector.getBeanInfo 
        java.beans.PropertyEditorFinder.registerEditor
        java.beans.PropertyEditorFinder.setEdiorSearchPath 
        javax.imageio.ImageIO.createImageInputStream 
        javax.imageio.ImageIO.createImageOutputStream 
        javax.imageio.ImageIO.getUseCache
        javax.imageio.ImageIO.setCacheDirectory
        javax.imageio.ImageIO.setUseCache 
        javax.print.StreamPrintServiceFactory.lookupStreamPrintServices
        javax.print.PrintServiceLookup.lookupDefaultPrintService 
        javax.print.PrintServiceLookup.lookupMultiDocPrintServices
        javax.print.PrintServiceLookup.lookupPrintServices
        javax.print.PrintServiceLookup.registerService 
        javax.print.PrintServiceLookup.registerServiceProvider

