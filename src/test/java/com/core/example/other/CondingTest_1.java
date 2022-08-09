package com.core.example.other;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CondingTest_1 {


    @Test
    void 리스트_회전() {
        List<Integer> a = Arrays.asList(new Integer[] {1, 2, 3});
        List<Integer> rotate = Arrays.asList(new Integer[] {1, 2, 3, 4});

        List<Integer> indices = new ArrayList<>();
        List<Integer> cloneA = a.stream().collect(Collectors.toList());
        int size = a.size() - 1;
        for (int num : rotate) {
            // num 에 해당되는 만큼 리스트 회전인데 rotate 메소드는 회전 방향이 오른쪽임,
            // 같은 효과를 내기 위해서 오른쪽으로 리스트 길이만큼 도는 횟수에서 돌려야 하는 횟수를 빼서 계산한다.
            Collections.rotate(cloneA, cloneA.size() - num);

            // 우선 첫번째 키와 첫번째 값을 저장
            int maxKey = 0;
            int maxValue = cloneA.get(maxKey);
            for (int i=1; i<cloneA.size(); i++) {
                if (maxValue < cloneA.get(i)) { // 저장해둔 값보다 크다면  값 교체
                    maxKey = i;
                    maxValue = cloneA.get(maxKey);
                }
            }
            // 결과 저장
            indices.add(maxKey);

            // 클론a 는 다시 a로 리셋
            cloneA = a.stream().collect(Collectors.toList());

            indices.forEach(System.out::print);
            System.out.println();
        }

//        for (int i=0; i<rotate.size(); i++) {
//            Collections.rotate(a, rotate.get(i));
//            a.forEach(System.out::print);
//            System.out.println();

//
//
//            int num = rotate.get(i);
//
//            int current = a.get(0);
//            int last = a.get(size);
//
//            for (int j=0; j<num; j++) {
//                int next = a.get(j+1);
//
//                a.add((j+1), current);
//                a.add(0, last);
//
//                current = next;
//                last = a.get(size);
//
//                a.forEach(System.out::print);
//            }

            for (int k=0; k<size; k++) {

            }
        }
//    }
}
