<!doctype html>
 <%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html lang="en">
    
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Oracle PaaS Rest API SDK Demo</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#accordion" ).accordion({
      heightStyle: "content"
    });
  } );
  </script>
</head>
<body>

<div id="accordion">
  <h3>SDK Demo</h3>
  <div>
    <p>
    <html:form action="SDKDemo"> <html:submit value="Run SDK Demo" /></html:form> <br>
    <br>
        si = new ServiceInstances();<br>
        ms = new ManagedServers();<br>
        HTTPResult result = si.viewAllInstances();<br>
        <br>
        System.out.println("Testing get all instances....");<br>
        System.out.println(result.getStatus());<br>
        System.out.println(result.getContent());<br>
        <br>
        System.out.println("Testing get all servers....");<br>
        result = ms.viewAllServers("GenPactDemoJCS");<br>       
        System.out.println(result.getStatus());<br>
        System.out.println(result.getContent());<br>
    </p>
  </div>
  <h3>Jersey Demo</h3>
  <div>
    <p>
        <html:form action="JerseyDemo"> <html:submit value="Run Jersey Demo" /></html:form> <br>
        <br>
     ClientConfig configuration = new ClientConfig();<br>
        int timeout = PropertyLoader.getTimeout();<br>
        System.out.println("Testing get all instances....");<br>
        if (timeout != 1) {<br>
            configuration.property(ClientProperties.CONNECT_TIMEOUT, timeout);<br>
            configuration.property(ClientProperties.READ_TIMEOUT, timeout);<br>
        }<br>
        if (!"".equals(PropertyLoader.getProxyHost()) && !"".equals(PropertyLoader.getProxyPort())) {<br>
            System.setProperty("https.proxyHost", PropertyLoader.getProxyHost());<br>
            System.setProperty("https.proxyPort", PropertyLoader.getProxyPort());<br>
        }<br>
        if (!"".equals(PropertyLoader.getProxyUser()) && !"".equals(PropertyLoader.getProxyPassword())) {<br>
            configuration.property(ClientProperties.PROXY_USERNAME, PropertyLoader.getProxyUser())<br>
                    .property(ClientProperties.PROXY_PASSWORD, PropertyLoader.getProxyPassword());<br>
        }<br>
        <br>
        httpClient = ClientBuilder.newClient(configuration);<br>
        if (!"".equals(PropertyLoader.getUser()) && !"".equals(PropertyLoader.getPassword())) {<br>
            httpClient.register(HttpAuthenticationFeature.basic(PropertyLoader.getUser(), PropertyLoader.getPassword()));<br>
        }<br>

        prefixURL = CommonConstants.USHOST + CommonConstants.ENDPOINTPREFIX + "/" + PropertyLoader.getIdentityDomain() + "/";<br>
        if (!"".equals(PropertyLoader.getRegion())) {<br>
            String jcsHost = CommonConstants.RegionHost.findHostByRegion(PropertyLoader.getRegion());<br>
            if (jcsHost != null) {<br>
                prefixURL = jcsHost + CommonConstants.ENDPOINTPREFIX + "/" + PropertyLoader.getIdentityDomain() + "/";<br>
            }<br>
        }<br>
        <br>
        WebTarget target = httpClient.target(prefixURL);<br>
        Invocation.Builder builder = target.request();<br>
        requestHeaders = new MultivaluedStringMap();<br>
        requestHeaders.putSingle(CommonConstants.TENENTHEADER, PropertyLoader.getIdentityDomain());<br>

        if (requestHeaders != null && !requestHeaders.isEmpty()) {<br>
            for (Map.Entry<String, List<String>> entry : requestHeaders.entrySet()) {<br>
                String key = entry.getKey();<br>
                List<String> values = entry.getValue();<br>
        <br>
                for (String value : values) {<br>
                    builder.header(key, value);<br>
                }<br>
            }<br>
        }<br>
        <br>
        Response response;<br>
        response = builder.get();<br>
        int status = response.getStatus();<br>
        Map respHeaders = response.getHeaders();<br>
        String content = (response.hasEntity() ? response.readEntity(String.class) : "");<br>
        System.out.println("status is : " + status);<br>
        System.out.println("content is : " + content);<br>
<br>
        System.out.println("Testing get all servers....");<br>
        target = httpClient.target(prefixURL + "GenPactDemoJCS" + "/servers");<br>
        builder = target.request();<br>
        requestHeaders = new MultivaluedStringMap();<br>
        requestHeaders.putSingle(CommonConstants.TENENTHEADER, PropertyLoader.getIdentityDomain());<br>
<br>
        if (requestHeaders != null && !requestHeaders.isEmpty()) {<br>
            for (Map.Entry<String, List<String>> entry : requestHeaders.entrySet()) {<br>
                String key = entry.getKey();<br>
                List<String> values = entry.getValue();<br>
                for (String value : values) {<br>
                    builder.header(key, value);<br>
                }<br>
            }<br>
        }<br>
        response = builder.get();<br>
        status = response.getStatus();<br>
        respHeaders = response.getHeaders();<br>
        content = (response.hasEntity() ? response.readEntity(String.class) : "");<br>
        System.out.println("status is : " + status);<br>
        System.out.println("content is : " + content);<br>
    </p>
  </div>
  <h3>Java Demo</h3>
  <div>
    <p>
        <html:form action="JavaDemo"> <html:submit value="Run Java Demo" /></html:form> <br>
        <br>
        System.out.println("Testing get all instances....");<br>
        prefixURL = CommonConstants.USHOST + CommonConstants.ENDPOINTPREFIX + "/" + PropertyLoader.getIdentityDomain() + "/";<br>
        if (!"".equals(PropertyLoader.getRegion())) {<br>
            String jcsHost = CommonConstants.RegionHost.findHostByRegion(PropertyLoader.getRegion());<br>
            if (jcsHost != null) {<br>
                prefixURL = jcsHost + CommonConstants.ENDPOINTPREFIX + "/" + PropertyLoader.getIdentityDomain() + "/";<br>
            }<br>
        }<br>
        try {<br>
            URL restServiceURL = new URL(prefixURL);<br>
            if (!"".equals(PropertyLoader.getProxyHost()) && !"".equals(PropertyLoader.getProxyPort())) {<br>
                System.setProperty("https.proxyHost", PropertyLoader.getProxyHost());<br>
                System.setProperty("https.proxyPort", PropertyLoader.getProxyPort());<br>
            }<br>
            HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();<br>
            httpConnection.setRequestMethod("GET");<br>
            String auth = PropertyLoader.getUser() + ":" + PropertyLoader.getPassword();<br>
            httpConnection.setRequestProperty("Authorization", "Basic " + Base64.getEncoder().encodeToString(auth.getBytes()));<br>
            httpConnection.setRequestProperty("X-ID-TENANT-NAME", PropertyLoader.getIdentityDomain());<br>
            if (httpConnection.getResponseCode() != 200) {<br>
                throw new RuntimeException("HTTP GET Request Failed with Error code : "<br>
                        + httpConnection.getResponseCode());<br>
            }<br>
            BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(<br>
                    (httpConnection.getInputStream())));<br>
            String output;<br>
            System.out.println("Output from Server:  \n");<br>
<br>
            while ((output = responseBuffer.readLine()) != null) {<br>
                System.out.println(output);<br>
            }<br>
<br>
            httpConnection.disconnect();<br>
        } catch (MalformedURLException e) {<br>
            e.printStackTrace();<br>
        } catch (IOException e) {<br>
            e.printStackTrace();<br>
        }<br>
       <br>
        System.out.println("Testing get all servers....");<br>
        try {<br>
            URL restServiceURL = new URL(prefixURL + "GenPactDemoJCS" + "/servers");<br>
            if (!"".equals(PropertyLoader.getProxyHost()) && !"".equals(PropertyLoader.getProxyPort())) {<br>
                System.setProperty("https.proxyHost", PropertyLoader.getProxyHost());<br>
                System.setProperty("https.proxyPort", PropertyLoader.getProxyPort());<br>
            }<br>
            HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();<br>
            httpConnection.setRequestMethod("GET");<br>
<br>
            String auth = PropertyLoader.getUser() + ":" + PropertyLoader.getPassword();<br>
            httpConnection.setRequestProperty("Authorization", "Basic " + Base64.getEncoder().encodeToString(auth.getBytes()));<br>
            httpConnection.setRequestProperty("X-ID-TENANT-NAME", PropertyLoader.getIdentityDomain());<br>
            if (httpConnection.getResponseCode() != 200) {<br>
                throw new RuntimeException("HTTP GET Request Failed with Error code : "<br>
                        + httpConnection.getResponseCode());<br>
            }<br>
<br>
            BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(<br>
                    (httpConnection.getInputStream())));<br>
            String output;<br>
            System.out.println("Output from Server:  \n");<br>
            while ((output = responseBuffer.readLine()) != null) {<br>
                System.out.println(output);<br>
            }<br>
<br>
            httpConnection.disconnect();<br>
        } catch (MalformedURLException e) {<br>
            e.printStackTrace();<br>
        } catch (IOException e) {<br>
            e.printStackTrace();<br>
        }<br>
    </p>
  </div>
</div>
 
 
</body>
</html>