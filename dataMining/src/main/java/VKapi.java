import org.json.JSONArray;
import org.json.JSONObject;

import javax.json.Json;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

class VKapi {
    private String access_token = "f234ca6e15bd8b222cf66201138cbd6971c14f768416cef9b6318b838d1b7fb58cebc046a0680e929be7f";

    Set<String> getYourGroupsTeg(int id) {
        Set<String> set = new HashSet<>();
        String url = "https://api.vk.com/method/groups.get?v=5.52&user_id=" + id + "&access_token=" + access_token;
        String line = "";
        try {
            URL url2 = new URL(url);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url2.openStream()));
            line = reader.readLine();
            reader.close();

        } catch (IOException e) {
            // ...
        }
        try {
            JSONObject object = new JSONObject(line).getJSONObject("response");
            JSONArray array = object.getJSONArray("items");
            String line1 = "";
            for (int j = 0; j < array.length(); j++) {
                String urlGroupTeg = "https://api.vk.com/method/groups.getById?v=5.52&group_id=" + (int) array.get(j) + "&fields=activity&description&access_token=" + access_token;

                URL url2 = new URL(urlGroupTeg);
                BufferedReader reader = new BufferedReader(new InputStreamReader(url2.openStream()));
                line1 = reader.readLine();
                reader.close();

                JSONArray object1 = new JSONObject(line1).getJSONArray("response");
                String activity = object1.getJSONObject(0).get("activity").toString();
                set.add(activity);
                Thread.sleep(350);
            }
        } catch (Exception e) {

        }
        return set;

    }

    ArrayDeque<Integer> getFriends(int id) {
        //Ранее описанный код получения списка сообщений
        String url = "https://api.vk.com/method/friends.get?v=5.52&user_id=" + id + "&access_token=" + access_token;
        String line = "";
        try {
            URL url2 = new URL(url);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url2.openStream()));
            line = reader.readLine();
            reader.close();

        } catch (IOException e) {
            // ...
        }
        JSONObject object = new JSONObject(line).getJSONObject("response");
        JSONArray array = object.getJSONArray("items");
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        for (int i = 0; i < array.length(); i++) {
            int obj = (int) array.get(i);
            arrayDeque.add(obj);
        }
        return arrayDeque;
    }

    String getName(int id) {
        String name = "";
        String url = "https://api.vk.com/method/users.get?v=5.52&user_id=" + id + "&access_token=" + access_token;
        String line = "";
        try {
            URL url2 = new URL(url);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url2.openStream()));
            line = reader.readLine();
            reader.close();

        } catch (IOException e) {
            // ...
        }
        try {
            JSONArray object = new JSONObject(line).getJSONArray("response");
            String firstName = object.getJSONObject(0).get("first_name").toString();
            String lastName = object.getJSONObject(0).get("last_name").toString();
            name = firstName + " " + lastName;
        }catch (Exception e){

        }
        return name;
    }

    TreeMap<Integer, Integer> getInfoAboutGroup(ArrayDeque<Integer> arrayDeque, Set<String> set) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i : arrayDeque) {
            String url = "https://api.vk.com/method/groups.get?v=5.52&user_id=" + i + "&access_token=" + access_token;
            String line = "";
            try {
                URL url2 = new URL(url);
                BufferedReader reader = new BufferedReader(new InputStreamReader(url2.openStream()));
                line = reader.readLine();
                reader.close();

            } catch (IOException e) {
                // ...
            }
            try {
                JSONObject object = new JSONObject(line).getJSONObject("response");
                JSONArray array = object.getJSONArray("items");
                String line1 = "";
                int c = 0;
                for (int j = 0; j < array.length(); j++) {
                    String urlGroupTeg = "https://api.vk.com/method/groups.getById?v=5.52&group_id=" + (int) array.get(j) + "&fields=activity&description&access_token=" + access_token;

                    URL url2 = new URL(urlGroupTeg);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(url2.openStream()));
                    line1 = reader.readLine();
                    reader.close();

                    JSONArray object1 = new JSONObject(line1).getJSONArray("response");
                    String activity = object1.getJSONObject(0).get("activity").toString();
                    if (set.contains(activity)) {
                        c++;
                        map.put(i, c);
                    }
                    Thread.sleep(350);
                }
            } catch (Exception e) {

            }
        }
        return map;
    }
}
