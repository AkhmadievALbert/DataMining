import java.util.*;

public class Main {
    public static void main(String[] args) {
        VKapi vkAPI = new VKapi();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your VK id: ");
        int id = sc.nextInt();

        Set<String> set = vkAPI.getYourGroupsTeg(id);
        ArrayDeque<Integer> friends = vkAPI.getFriends(id);
        TreeMap<Integer, Integer> result = vkAPI.getInfoAboutGroup(friends, set);
        for (Map.Entry<Integer, Integer> e:result.entrySet()) {
            System.out.println(vkAPI.getName(e.getKey()) + " = " + e.getValue());
        }

    }
}
