package api;

public class main_getapi {
    public static void main(String[] args) throws Exception {

        String API_KEY = "OsGIgKJNugsIPURr6oRDaTIe5ViaXvz3cFF6fihxgdCuwHQ4pM";
        String API_SECRET = "o11EweL12UhdW7M11gCXoCKBPQGEphTy8FBwBT9x";

        // 1. 获取 access token
        String accessToken = api_token.getAccessToken(API_KEY, API_SECRET);
        System.out.println("Access token = " + accessToken);

        // 2. 调用 Petfinder API
        petfinder_api.getCats(accessToken);

    }

}
