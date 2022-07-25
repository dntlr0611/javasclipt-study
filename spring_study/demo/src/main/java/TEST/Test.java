// package TEST;

// import java.io.BufferedReader;
// import java.io.BufferedWriter;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.io.OutputStreamWriter;
// import java.util.ArrayList;
// import java.util.Iterator;
// import java.util.Set;
// import java.util.StringTokenizer;
// import java.util.stream.Stream;

// public class Test {
//     public static void main(String[] args) throws IOException {
//         BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//         BufferedWriter wf = new BufferedWriter(new OutputStreamWriter(System.out));

//         StringTokenizer st = new StringTokenizer(bf.readLine());
//         String a = st.nextToken();
//         System.out.println(a);
//         String[] data = new String[]{a};
//         Stream.of(data).forEach(d -> {
//             String name = d.split(",")[0];
//             String number = d.split(",")[1];
//             add(name, number);
//         });
//         Set<String> overlapN = map.keySet();
//         Iterator<String> itr = overlapN.iterator();
//         while (itr.hasNext()) {
//             wf.write(itr);
//             wf.flush();
//         }
//         wf.close();
//     }

//     static void add(String name, String number) {
//         if (map.containsKey(name)) {
//             map.get(name).add(number);
//         } else {
//             ArrayList<String> dataset = new ArrayList<>();
//             dataset.add(number);
//             map.put(name, dataset);
//         }
//     }

// }
