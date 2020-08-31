(ns scratch.crime-test
  (:require [clojure.test :refer :all]
            [scratch.crime :refer :all]))

(deftest fips-code-test
  (is (= "12345" (fips-code {:fips_state_code "12" :fips_county_code "345"}))))

(deftest most-prevalent-test
  (testing "prevalence calculation"
   (is (= (float 21/27158) (get-in (most-prevalent "resources/ucr_normalized_2008.json" :arson) [0 1])))))