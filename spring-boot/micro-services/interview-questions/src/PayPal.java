import java.util.*;


/**
You are a developer for a university. Your current project is to develop a system for students to find courses they share with friends. The university has a system for querying courses students are enrolled in, returned as a list of (ID, course) pairs.

Write a function that takes in a collection of (student ID number, course name) pairs and returns, for every pair of students, a collection of all courses they share.

Sample Input:

enrollments1 = [
  ["58", "Linear Algebra"],
  ["94", "Art History"],
  ["94", "Operating Systems"],
  ["17", "Software Design"],
  ["58", "Mechanics"],
  ["58", "Economics"],
  ["17", "Linear Algebra"],
  ["17", "Political Science"],
  ["94", "Economics"],
  ["25", "Economics"],
  ["58", "Software Design"],
]

Sample Output (pseudocode, in any order):

find_pairs(enrollments1) =>
{
  "58,17": ["Software Design", "Linear Algebra"]
  "58,94": ["Economics"]
  "58,25": ["Economics"]
  "94,25": ["Economics"]
  "17,94": []
  "17,25": []
}

Additional test cases:

Sample Input:

enrollments2 = [
  ["0", "Advanced Mechanics"],
  ["0", "Art History"],
  ["1", "Course 1"],
  ["1", "Course 2"],
  ["2", "Computer Architecture"],
  ["3", "Course 1"],
  ["3", "Course 2"],
  ["4", "Algorithms"]
]

Sample output:

find_pairs(enrollments2) =>
{
  "1,0":[]
  "2,0":[]
  "2,1":[]
  "3,0":[]
  "3,1":["Course 1", "Course 2"]
  "3,2":[]
  "4,0":[]
  "4,1":[]
  "4,2":[]
  "4,3":[]
}

Sample Input:
enrollments3 = [
  ["23", "Software Design"],
  ["3", "Advanced Mechanics"],
  ["2", "Art History"],
  ["33", "Another"],
]

Sample output:

find_pairs(enrollments3) =>
{
  "23,3": []
  "23,2": []
  "23,33":[]
  "3,2":  []
  "3,33": []
  "2,33": []
}

All Test Cases:
find_pairs(enrollments1)
find_pairs(enrollments2)
find_pairs(enrollments3)

Complexity analysis variables:

n: number of student,course pairs in the input
s: number of students
c: total number of courses being offered (note: The number of courses any student can take is bounded by a small constant)
*/
public class PayPal {

    public Map<String, List<String>> mapCommon(Map<String, List<String>> data) {
        Map<String, List<String>> result = new HashMap<>();
        List<String> students = new ArrayList<>(data.keySet());

        for (int i = 0; i < students.size(); i++) {
            String student1 = students.get(i);
            Set<String> courses1 = new HashSet<>(data.get(student1));

            for (int j = i + 1; j < students.size(); j++) { // avoid duplicate pairs
                String student2 = students.get(j);
                Set<String> courses2 = new HashSet<>(data.get(student2));

                // find intersection
                courses1.retainAll(courses2);

                result.put(student1 + "," + student2, new ArrayList<>(courses1));

                // reset courses1 for next iteration
                courses1 = new HashSet<>(data.get(student1));
            }
        }

        return result;
    }


    // Step 1: map<String, <List<String>> data
    public Map<String, List<String>> mapData (String[][] original){
        Map<String, List<String>> data = new HashMap<>();

        // [[id, course], ...] -> [id, course]
        for(String[] innerList : original){
            String key = innerList[0];
            String value = innerList[1];

            List<String> course = data.getOrDefault(key, new ArrayList<>());
            course.add(value);

            data.put(key, course);
        }

        return data;
    }

    public Map<String, List<String>> mapCommon2(Map<String, List<String>> data){
        StringBuilder keys = new StringBuilder();
        Map<String, List<String>> result = new HashMap<>();
        for(Map.Entry<String, List<String>> entries : data.entrySet()){
            String firstKey = entries.getKey();
            for(Map.Entry<String, List<String>> newEntries : data.entrySet()){
                String secondKey = newEntries.getKey();
                if(!firstKey.equals(secondKey)){
                    keys.append(firstKey).append(", ").append(secondKey);
                    StringBuilder duplicateKey = new StringBuilder();
                    duplicateKey.append(secondKey).append(", ").append(firstKey);
                    if(!result.containsKey(duplicateKey.toString())){
                        result.put(keys.toString(), new ArrayList<>());

                        Map<String, Integer> freq = new HashMap<>();

                        // map first id
                        for(String course : data.get(firstKey)){
                            freq.put(course, freq.getOrDefault(course, 0) + 1);
                        }

                        // map second id
                        for(String course : data.get(secondKey)){
                            freq.put(course, freq.getOrDefault(course, 0) + 1);
                        }

                        // get courses with freq > 1
                        // duplicates in both users
                        List<String> duplicateCourses = new ArrayList<>();
                        for(Map.Entry<String, Integer> courseEntries : freq.entrySet()){
                            if(courseEntries.getValue() > 1){
                                duplicateCourses.add(courseEntries.getKey());
                            }
                        }

                        result.get(keys.toString()).addAll(duplicateCourses);
                    }
                }

                keys = new StringBuilder();
            }
        }
        return result;
    }

    // step 2 :
    public void displayData(Map<String, List<String>> data){
        for(Map.Entry<String, List<String>> entries : data.entrySet()){
            System.out.print(entries.getKey() + ": ");
            List<String> values = entries.getValue();
            for(int i = 0; i < values.size(); i++){
                System.out.print(values.get(i));
                if (i != values.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println(); // move to next line after printing all courses
        }
    }
}
