package io.mangue.config.multitenance;

public class TenantContextHolder {

	private static final ThreadLocal<String> appId = new ThreadLocal<String>();
    private static final ThreadLocal<String> subdomain = new ThreadLocal<String>();
    private static final ThreadLocal<String> uniqueSimpleHash = new ThreadLocal<String>();
    private static final ThreadLocal<Boolean> consoleRequest = new ThreadLocal<Boolean>();
    private static final ThreadLocal<Boolean> appApiRequest = new ThreadLocal<Boolean>();

	public static String getTenantAppId() {
		return appId.get();
	}

	public static String getTenantSubdomain() {
		return subdomain.get();
	}

    public static String getTenantUniqueSimpleHash(){
        return uniqueSimpleHash.get();
    }

    public static Boolean isTenantAppApiRequest(){
        return appApiRequest.get();
    }

    public static Boolean isTenantConsoleRequest(){
        return consoleRequest.get();
    }

	public static void setTenantAppId(String tenantAppId) {
		appId.set(tenantAppId);
	}

	public static void setTenantSubdomain(String tenantSubdomain) {
		subdomain.set(tenantSubdomain);
	}

    public static void setTenantUniqueSimpleHash(String tenantUniqueSimpleHash) {
        uniqueSimpleHash.set(tenantUniqueSimpleHash);
    }

    public static void setTenantAppApiRequest(Boolean isApiRequest){
        appApiRequest.set(isApiRequest);
    }

    public static void setTenantConsoleRequest(Boolean isConsoleRequest){
        consoleRequest.set(isConsoleRequest);
    }

}