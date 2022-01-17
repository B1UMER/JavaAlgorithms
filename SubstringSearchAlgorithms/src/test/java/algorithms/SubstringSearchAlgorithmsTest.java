package algorithms;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import org.junit.jupiter.api.*;
import java.util.List;
import org.openjdk.jmh.annotations.Measurement;

class SubstringSearchAlgorithmsTest {
        final String INPUT_DEFAULT = "What does the brain matter";
        final String INPUT_MANYMATCHES = "What does the brain matter does the the does the";
        final String PATTERN_THE = "the";
        final String PATTERN_ABRA = "abra";
        final String INPUT_CUSTOM = "ababaracadadabra";
        final String PATTERN_CUSTOM_1 = "aba";
        final String PATTERN_CUSTOM_2 = "ada";
        final String LINE_DELIMETER = "------------------------------------------------------------";
        final String DIRECT_SEARCH_ALG_NAME = "directSearch";
        final String KMP_SEARCH_ALG_NAME = "kmpSearch";
        final String RK_SEARCH_ALG_NAME = "rkSearch";
        final String BM_SEARCH_ALG_NAME = "bmSearch";

        @Test
        public void directSearchOneMatches () {
            Assertions.assertEquals(List.of(10),SubstringSearchAlgorithms.directSearch(INPUT_DEFAULT, PATTERN_THE));
        }

        @Test
        public void directSearchNoneMatches (){
            Assertions.assertEquals(List.of(),SubstringSearchAlgorithms.directSearch(INPUT_DEFAULT, PATTERN_ABRA));
        }

        @Test
        public void directSearchManyMatches (){
            Assertions.assertEquals(List.of(10,32,36,45),SubstringSearchAlgorithms.directSearch(INPUT_MANYMATCHES, PATTERN_THE));
        }


        @Test
        public void kmpSearchOneMatches () {
            Assertions.assertEquals(List.of(10),SubstringSearchAlgorithms.kmpSearch(INPUT_DEFAULT, PATTERN_THE));
        }

        @Test
        public void kmpSearchNoneMatches (){
            Assertions.assertEquals(List.of(),SubstringSearchAlgorithms.kmpSearch(INPUT_DEFAULT, PATTERN_ABRA));
        }

        @Test
        public void kmpSearchManyMatches (){
            Assertions.assertEquals(List.of(10,32,36,45),SubstringSearchAlgorithms.kmpSearch(INPUT_MANYMATCHES, PATTERN_THE));
        }

        @Test
        public void bmSearchManyMatches (){
            Assertions.assertEquals(List.of(10,32,36,45),SubstringSearchAlgorithms.bmSearch(INPUT_MANYMATCHES, PATTERN_THE));
        }

        @Test
        public void bmSearchOneMatches () {
            Assertions.assertEquals(List.of(10),SubstringSearchAlgorithms.bmSearch(INPUT_DEFAULT, PATTERN_THE));
        }

        @Test
        public void bmSearchNoneMatches (){
            Assertions.assertEquals(List.of(),SubstringSearchAlgorithms.bmSearch(INPUT_DEFAULT, PATTERN_ABRA));
        }


        @Test
        public void rkSearchManyMatches (){
            Assertions.assertEquals(List.of(10,32,36,45),SubstringSearchAlgorithms.rkSearch(INPUT_MANYMATCHES, PATTERN_THE));
        }

        @Test
        public void rkSearchOneMatches () {
            Assertions.assertEquals(List.of(10),SubstringSearchAlgorithms.rkSearch(INPUT_DEFAULT, PATTERN_THE));
        }

        @Test
        public void rkSearchNoneMatches (){
            Assertions.assertEquals(List.of(),SubstringSearchAlgorithms.rkSearch(INPUT_DEFAULT, PATTERN_ABRA));
        }

        @Test
        public void specialTest()
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
          List<Method> methodList;
            methodList = List.of(
              SubstringSearchAlgorithms.class.getMethod(DIRECT_SEARCH_ALG_NAME, String.class, String.class),
              SubstringSearchAlgorithms.class.getMethod(KMP_SEARCH_ALG_NAME, String.class, String.class),
              SubstringSearchAlgorithms.class.getMethod(RK_SEARCH_ALG_NAME, String.class, String.class),
              SubstringSearchAlgorithms.class.getMethod(BM_SEARCH_ALG_NAME, String.class, String.class)
          );
          for (Method method: methodList) {
            List<Integer> searchResultsCustom1 = (List<Integer>) method.invoke(null,INPUT_CUSTOM,PATTERN_CUSTOM_1);
            printOutput(method.getName(), PATTERN_CUSTOM_1, searchResultsCustom1);
            List<Integer> searchResultsCustom2 = (List<Integer>) method.invoke(null,INPUT_CUSTOM,PATTERN_CUSTOM_2);
            printOutput(method.getName(), PATTERN_CUSTOM_2, searchResultsCustom2);
          }
        }

        private void printOutput(String methodName,String pattern, List<Integer> searchResults1){
          System.out.println(LINE_DELIMETER);
          System.out.format("Алогоритм поиска: %s\t| Строка для поиска: %s\t| Подстрока: %s",
              methodName, INPUT_CUSTOM, pattern)
              .println();
          System.out.format("Результаты поиска: %s", convertListToString(searchResults1)).println();
        }

        private String convertListToString(List<Integer> list){
          StringBuilder sb = new StringBuilder();
          sb.append("индекс позиции: ");
          list.forEach(pos -> sb.append(pos).append(" "));
          return sb.toString();
        }
}