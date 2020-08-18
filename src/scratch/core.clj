(ns scratch.core
  (:require
   [cheshire.core :as cheshire]
   [clojure.pprint :as p]))

(defn pow
  "Raises base to the given power. For instance, (pow 3 2) returns three squared, or nine."
  [base power]
  (apply * (repeat power base)))

(def data (cheshire/parse-string (slurp "resources/ucr-normalized-2008.json") true))

(->> data (sort-by :driving_under_influence) 
     (take-last 10) 
     (map #(select-keys % [:driving_under_influence :fips_county_code :fips_state_code]))
     p/pprint)
