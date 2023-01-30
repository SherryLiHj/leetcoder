import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int[] twoSum(int[] nums, int target){
        for(int i=0; i < nums.length-1; i++){
            for(int j=i+1; j < nums.length; j++){
                if(nums[i]+nums[j]==target){
                    return new int[]{i,j}; // 使用花括号
                }
            }
        }
        return null;
    }
    
    /**********************************************   hash  **********************************************/
    
  // 1. two sum (hash)
    public int[] twoSum_hash(int[] nums, int target){
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0; i < nums.length; i++){
            int temp = target-nums[i];
            if(map.containsKey(temp)){ // 把要判断是否存在的值作为key
                return new int[]{i,map.get(temp)};
            }
            map.put(nums[i],i); // 当两个嵌套循环+外层i+内层初始i+1(在当前值往后判断) 可以使用单层循环+在每层循环的最后加入(在当前值往前判断)
        }
        return null;
    }
    
    // 15. three sum（hash / double pointer）
    // hash
    public List<List<Integer>> threeSum(int[] nums){
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums); // 先给数组排序，方便之后查重
        for(int i=0; i<nums.length-2; i++){
            Map<Integer,Integer> map = new HashMap<>();
            for(int j=i+1; j<nums.length; j++){
                int temp = 0 - nums[i] - nums[j];
                if(map.containsKey(temp)){
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(temp);
                    list.add(nums[j]);
                    if(!result.contains(list))
                        result.add(list);
                }
                map.put(nums[j],j);
            }
        }
        return result;
    }
    
    // double pointer
    public List<List<Integer>> threeSum_double(int[] nums){
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int len = nums.length;
        Arrays.sort(nums);
        for (int i=0; i<len-2; i++){
            if(i>0 && nums[i] == nums[i-1])
                continue;
            for (int j=i+1; j<len-1; j++){
                if(j>i+1 && nums[j] == nums[j-1])
                    continue;
                int k=len-1;
                while(k>j && nums[j]+nums[k]+nums[i]>0){ // 循环里面的语句要尽可能少，取代下面循环中操作过多的for
                    k--;
                }
                if(k==j) break;
                if (nums[j] + nums[k] + nums[i] == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    result.add(list);
                }
//                for (int k = len-1; k>j; k--) {
//                    if(nums[i] + nums[j] + nums[k] < 0)
//                        break;
//                    if ((k == len - 1 || nums[k] != nums[k + 1]) && nums[i] + nums[j] + nums[k] == 0) {
//                        List<Integer> list = new ArrayList<>();
//                        list.add(nums[i]);
//                        list.add(nums[j]);
//                        list.add(nums[k]);
//                        result.add(list);
//                    }
//                }
            }
        }
        return result;
    }
    
    // 13. Roman to Integer
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        int result = 0;
        int pre = 9999;
        for(int i=0; i<s.length(); i++){
            int temp = map.get(s.charAt(i));
            if(pre < temp)
                result = result + temp - 2*pre;
            else
                result += temp;
            pre = temp;
        }
        return result;
    }
    
    
    /**********************************************   Iteration / Recursion  **********************************************/
    // 20. Merge Two Sorted Lists( Iteration / Recursion)
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) { // Iteration
        ListNode prehead = new ListNode(0,null);
        ListNode result = prehead;
        while(list1!=null && list2!=null){
            if(list1.val < list2.val){
                result.next = list1;
                list1 = list1.next;
            } else {
                result.next = list2;
                list2 = list2.next;
            }
            result = result.next;
        }
        if(list1 != null)
            result.next = list1;
        else
            result.next = list2;
        return prehead.next;
    }

    public ListNode mergeTwoListsRecursion(ListNode list1, ListNode list2) { // Recursion 只关注这一层要干什么,返回什么,至于下一层(规模减1),不管
        if(list1 == null)
            return list2;
        else if(list2 == null)
            return list1;
        else if(list1.val < list2.val) {
            list1.next = mergeTwoListsRecursion(list1.next, list2);
            return list1;
        }
        else {
            list2.next = mergeTwoListsRecursion(list1, list2.next);
            return list2;
        }
    }
    
    // 17. Letter Combinations Phone Number
    public List<String> letterCombinations(String digits) {
        if(digits.equals("")){
            return result;
        }
        Map<Character, String> map = new HashMap<Character,String>() {{
            put('2',"abc");
            put('3',"def");
            put('4',"ghi");
            put('5',"jkl");
            put('6',"mno");
            put('7',"pqrs");
            put('8',"tuv");
            put('9',"wxyz");
        }};
        int len = digits.length();
        List<String> letters = new ArrayList<String>();
        for(int i=0; i<len; i++){
            letters.add(map.get(digits.charAt(i)));
        }
        combine(letters, len, 0, new String(""), result);
        return result;
    }

    public void combine(List<String> letters,int len, int nd, String combination, List<String> result){
        if(nd >= len) {
            result.add(combination);
            return;
        }
        for(int i=0; i<letters.get(nd).length(); i++){
            combine(letters,len,nd+1,combination+letters.get(nd).charAt(i),result);
        }
    }
    
    /**********************************************   DP  **********************************************/
    // 62. Unique Paths( Dynamic Programming / Recursion)
    public int uniquePaths(int m, int n) { // DP f(m,n) = f(m-1,n) + f(m,n-1)
        int[][] result = new int[m][n];
//        result[0][1] = 1;
//        result[1][0] = 1;
//        for (int i=1; i<m; i++){
//            for (int j=1; j<n; j++){
//                result[i][j] = result[i-1][j] + result[i][j-1];
//            }
//        }
        for (int i=0; i<m; i++){
            for (int j=0; j<n; j++){
                if(i==0 || j==0)
                    result[i][j] = 1;
                else
                    result[i][j] = result[i-1][j] + result[i][j-1];
            }
        }
        return result[m-1][n-1];
    }

    public int uniquePaths_Recursion(int m, int n){ //Recursion
        if(m==1 || n==1)
            return 1;
        return uniquePaths_Recursion(m-1,n) + uniquePaths_Recursion(m,n-1);
    }
    
    
    // 70. Climbing Stairs ( Dynamic Programming )
    public int climbStairs(int n) { //DP f(n) = f(n-1) + f(n-2)
        int prepre = 1;
        if(n<=1)
            return prepre;
        int pre = 2;
        int result = 2;
        for(int i=2; i<n; i++){
            result = pre + prepre;
            prepre = pre;
            pre = result;
        }
        return result;
    }
    
    
    /**********************************************     **********************************************/
    // 14. Longest Common Prefix ( 首先找到最短的字符串长度 )
    public String longestCommonPrefix(String[] strs) {
        String result = new String("");
        int len = strs.length;
        int shortestLen = strs[0].length();
        boolean flag = true;
        for (int i=1; i<len; i++){
            if(shortestLen > strs[i].length())
                shortestLen = strs[i].length();
        }
        for(int i=0; i<shortestLen; i++){
            char temp = strs[0].charAt(i);
            for(int j=1; j<len; j++){
                if(temp != strs[j].charAt(i))
                    flag = false;
            }
            if(!flag)
                break;
            result = result + String.valueOf(temp);
        }
        return result;
    }
   
    /**********************************************    Stack  **********************************************/
    // 20. valid parentheses (stack)
    public boolean ValidParentheses(String s) {
        int len = s.length();
        char[] charArr = s.toCharArray();
        Stack<Character> st = new Stack<Character>();
        for (int i=0; i<len; i++){
            if(charArr[i] == '(') {
                st.push(')');
            } else if(charArr[i] == '[') {
                st.push(']');
            } else if(charArr[i] == '{') {
                st.push('}');
            } else if(st.isEmpty() || charArr[i] != st.pop()){ //st.isEmpty() || !st.isEmpty() && charArr[i] != st.pop()
                return false;
            }
        }
        if(st.empty())
            return true;
        return false;
    }
    
    // 1047. Remove All Adjacent Duplicates In String
    public String removeDuplicates(String s) { // 或者用StringBuilder模拟栈操作
        StringBuilder result = new StringBuilder();
        Stack<Character> st = new Stack<Character>();
        int len = s.length();
        st.push(s.charAt(0));
        for(int i=1; i < len; i++){
            char temp = s.charAt(i);
            if(!st.isEmpty() && st.peek() == temp){
                st.pop();
            } else {
                st.push(temp);
            }
        }
        while(!st.isEmpty()){
            result.append(st.pop());
        }
        return result.reverse().toString();
    }
    
    //739. Daily Temperatures
    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        Stack<Integer> st = new Stack<>(); // 存储没有计算出天数的天在temperatures中的位置
        int[] result = new int[len];
        for(int i=0; i < len; i++){
            while(!st.isEmpty() && temperatures[i] > temperatures[st.peek()]){
                result[st.peek()] = i - st.pop();
            }
            st.push(i);
        }
        return result;
    }
    
    //678. Valid Parenthesis String
    public boolean checkValidString(String s) {
        char[] cs = s.toCharArray();
//        Stack<Character> st = new Stack<Character>(); // 可以不用存储右括号
//        Stack<Character> star = new Stack<Character>(); // 存储 *
        // 可以不用存储）, 存储 *
        // 栈不存储char，存储位置编号，为了防止*与（配对，（在*后面的情况
        Stack<Integer> st = new Stack<Integer>(); // 可以不用存储右括号
        Stack<Integer> star = new Stack<Integer>(); // 存储 *

        for(int i=0; i<s.length(); i++){
            if(cs[i] == '(')
                st.push(i);
            else if(cs[i] == '*')
                star.push(i);
            else{
                if(!st.isEmpty())
                    st.pop();
                else if(!star.isEmpty())
                    star.pop();
                else
                    return false;
            }
        }
//        if(st.isEmpty())
//            result = true;
        // 不能只判断st是否为空，因为star可以和里面的左括号配对
        if(st.size() > star.size())
            return false;
        else{
            while(!st.isEmpty()){
                if(st.pop() > star.pop())
                    return false;
            }
            return true;
        }
    }

    /**********************************************  Hash Map / Hash Set  **********************************************/
    // 1160. Find Words That Can Be Formed by Characters
    public int countCharacters(String[] words, String chars) {
        // 也可以用数组代替hashmap存储字母个数
        int result = 0;
        // hash记录的是chars中各字母出现的次数
        HashMap<Character,Integer> letters = new HashMap<>();
        for(int i=0; i<chars.length(); i++){
            letters.put(chars.charAt(i),letters.getOrDefault(chars.charAt(i),0)+1);
        }
        for(String word : words){
            char[] cword = word.toCharArray();
            HashMap<Character,Integer> wl = new HashMap<>();
            for(int i=0; i<cword.length; i++){
                wl.put(cword[i],wl.getOrDefault(cword[i],0)+1);
            }
            boolean flag = true;
            for(char key : wl.keySet()){
                if(!letters.containsKey(key) || letters.get(key)<wl.get(key)){
                    flag = false;
                    break;
                }
            }
            if(flag)
                result += cword.length;
        }
        return result;
    }
    
    // 219. Contains Duplicate II
    // 滑动窗口
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        for(int i=0; i<nums.length; i++){
            if(set.contains(nums[i]))
                return true;
            set.add(nums[i]);
            if(set.size() > k){
                set.remove(nums[i-k]);
            }
        }
        return false;
    }
    

    /**********************************************  List  **********************************************/
    // 3. Longest Substring Without Repeating Characters
    public int lengthOfLongestSubstring(String s) {
        List<Character> sub_str = new ArrayList<>();
        int ans = 0;
        for(int i=0; i<s.length(); i++){
            if(!sub_str.contains(s.charAt(i))) {
                sub_str.add(s.charAt(i));
                ans = Math.max(ans,sub_str.size());
            }else{
                ans = Math.max(ans,sub_str.size());
//                for (; sub_str.get(j) != s.charAt(i); j++) {
//                    sub_str.remove(j);
//                } 每次的删除操作会影响字母的下标，因此这样行不通，会报超出界限的错误
                while(sub_str.get(0) != s.charAt(i)) {
                    sub_str.remove(0);
                }
                sub_str.remove(0);
                sub_str.add(s.charAt(i)); // 不要忘记这句
            }
        }

        // 方法二 Hash
        int n = s.length();
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0, start = 0; end < n; end++) {
            char alpha = s.charAt(end);
            if (map.containsKey(alpha)) {
                start = Math.max(map.get(alpha), start);
            }
            ans = Math.max(ans, end - start + 1);
            map.put(s.charAt(end), end + 1);
        }

        return ans;
    }
    
    public static void main(String[] args){
        Two_Sum ts = new Two_Sum();
        System.out.println(ts.twoSum(new int[]{2,7,11,5}, 9));
    }
    
}

