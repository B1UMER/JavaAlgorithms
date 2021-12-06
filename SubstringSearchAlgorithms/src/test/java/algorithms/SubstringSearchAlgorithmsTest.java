package algorithms;

import org.junit.jupiter.api.*;
import java.util.List;

class SubstringSearchAlgorithmsTest {
        final String INPUT_DEFAULT = "What does the brain matter";
        final String INPUT_MANYMATCHES = "What does the brain matter does the the does the";
        final String PATTERN_THE = "the";
        final String PATTERN_ABRA = "abra";

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

}