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
        List<Integer> indices = new ArrayList<>();

        List<Integer> a = Arrays.asList(new Integer[]{1, 2, 3});
        List<Integer> rotate = Arrays.asList(new Integer[]{1, 2, 3, 4});

        // 원본은 리셋을 위해 건들지 않고 클론 생성
        Integer[] cloneA = a.toArray(new Integer[a.size()]);
        int cloneASize = cloneA.length;

        for (int num : rotate) {
            // num 에 해당되는 만큼 왼쪽으로 회전
            int rotateCnt = cloneASize > num ? num : (num - cloneASize);

            int first = cloneA[0];
            for (int i=0; i<rotateCnt; i++) {
                for (int j=0; j<cloneASize; j++) {
                    if ((cloneASize - 1) == j) {
                        cloneA[j] = first;               // 첫번째 요소는 마지막 자리로
                    } else {
                        cloneA[j] = cloneA[j+1];
                    }
                }
                first = cloneA[0];
            }

            // 우선 첫번째 키와 첫번째 값을 저장
            int maxKey = 0;
            int maxValue = cloneA[maxKey];
            for (int i=1; i<cloneASize; i++) {
                if (maxValue < cloneA[i]) { // 저장해둔 값보다 크다면  값 교체
                    maxKey = i;
                    maxValue = cloneA[maxKey];
                }
            }
            // 결과 저장
            indices.add(maxKey);

            // 클론a 는 다시 a로 리셋
            cloneA = a.toArray(new Integer[a.size()]);

//            indices.forEach(System.out::print);
//            System.out.println();
        }
    }
}
