(ns scratch.core)

(defn pow
  "Raises base to the given power. For instance, (pow 3 2) returns three squared, or nine."
  [base power]
  (apply * (repeat power base)))

(use 'cheshire.core)
(parse-string (slurp "resources/ucr-normalized-2008.json"))