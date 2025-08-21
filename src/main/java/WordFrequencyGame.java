import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.Arrays;  // Add this import

public class WordFrequencyGame {
    public String getResult(String inputStr){


        if (inputStr.split("\\s+").length==1) {
            return inputStr + " 1";
        } else {

            try {
                //split the input string with 1 to n pieces of spaces
                String[] arr = inputStr.split("\\s+");

                List<Input> inputList = Arrays.stream(arr)
                        .map(s -> new Input(s, 1))
                        .collect(Collectors.toList());

                //get the map for the next step of sizing the same word
                Map<String, List<Input>> map =getListMap(inputList);

                List<Input> list = map.entrySet().stream().map(entry -> new Input(entry.getKey(), entry.getValue().size())).collect(Collectors.toList());
                inputList = list;

                inputList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                inputList.stream().map(w -> w.getValue() + " " + w.getWordCount()).forEach(joiner::add);
                return joiner.toString();
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }


    private Map<String,List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        //       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
        inputList.forEach(input -> {
            if (!map.containsKey(input.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            } else {
                map.get(input.getValue()).add(input);
            }
        });


        return map;
    }


}