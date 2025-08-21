import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.Arrays;  // Add this import

public class WordFrequencyGame {
    public String calculateWordFrequency(String input){


        if (input.split("\\s+").length==1) {
            return input + " 1";
        } else {

            try {
                String[] words = input.split("\\s+");

                List<Input> inputList = Arrays.stream(words)
                        .map(s -> new Input(s, 1))
                        .collect(Collectors.toList());

                //get the map for the next step of sizing the same word
                Map<String, List<Input>> groupedWords =groupWordsByValue(inputList);

                inputList = groupedWords.entrySet().stream().map(entry -> new Input(entry.getKey(), entry.getValue().size())).collect(Collectors.toList());

                inputList.sort((input1, input2) -> input2.getWordCount() - input1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                inputList.stream().map(w -> w.getValue() + " " + w.getWordCount()).forEach(joiner::add);
                return joiner.toString();
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }


    private Map<String,List<Input>> groupWordsByValue(List<Input> inputList) {
        Map<String, List<Input>> wordFrequencyMap = new HashMap<>();
        inputList.forEach(input -> {
            if (!wordFrequencyMap.containsKey(input.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                wordFrequencyMap.put(input.getValue(), arr);
            } else {
                wordFrequencyMap.get(input.getValue()).add(input);
            }
        });


        return wordFrequencyMap;
    }


}