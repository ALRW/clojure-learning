(ns macros.core-test
  (:require [clojure.test :refer [deftest testing is]]
            [macros.core :as m]))

(deftest when-macro
  (testing "works with positive condition"
    (let [result (m/when-macro (= 1 1) "hello")]
      (is (= "hello" result))))
  (testing "works with a negative condition"
    (let [result (m/when-macro false "hello")]
      (is (nil? result)))))

(deftest thread-last-macro
  (testing "thread last single form"
    (is (= 2 (m/thread-last (+ 1 1)))))

  (testing "thread last multiple forms"
    (let [result (m/thread-last (+ 1 1)
                                (- 3)
                                inc
                                (* 2)
                                )]
      (is (= 4 result)))))
