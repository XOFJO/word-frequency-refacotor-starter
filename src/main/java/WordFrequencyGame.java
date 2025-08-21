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
                List<Input> inputList = getInputList(input);
                
                Map<String, List<Input>> groupedWords = getWordFrequencyMap(inputList);

                StringJoiner joiner = getWordFrequencyArray(groupedWords);
                return joiner.toString();
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }

    private static StringJoiner getWordFrequencyArray(Map<String, List<Input>> groupedWords) {
        List<Input> inputList;
        inputList = groupedWords.entrySet().stream().map(entry -> new Input(entry.getKey(), entry.getValue().size())).collect(Collectors.toList());

        inputList.sort((input1, input2) -> input2.getWordCount() - input1.getWordCount());

        StringJoiner joiner = new StringJoiner("\n");
        inputList.stream().map(w -> w.getValue() + " " + w.getWordCount()).forEach(joiner::add);
        return joiner;
    }

    private static List<Input> getInputList(String input) {
        String[] words = input.split("\\s+");

        List<Input> inputList = Arrays.stream(words)
                .map(s -> new Input(s, 1))
                .collect(Collectors.toList());
        return inputList;
    }


    private Map<String,List<Input>> getWordFrequencyMap(List<Input> inputList) {
        Map<String, List<Input>> wordFrequencyMap = new HashMap<>();
        inputList.forEach(input -> {
            if (wordFrequencyMap.containsKey(input.getValue())) {
                wordFrequencyMap.get(input.getValue()).add(input);
            } else {
                ArrayList wordGroup = new ArrayList<>();
                wordGroup.add(input);
                wordFrequencyMap.put(input.getValue(), wordGroup);
            }
        });
        return wordFrequencyMap;
    }


}