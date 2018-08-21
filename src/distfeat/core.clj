(ns distfeat.core
  (:require [clojure.set :as set]
            [clojure.string :as string]
            [clojure.math.combinatorics :as combo]
            [distfeat.phonemes :as ph])
  (:gen-class))

(defn shared-features
  "Return a map of features and values shared by all elements in `phonemes`.
  `phonemes` should be a seq of sets of features."
  [phonemes]
  (into {} (reduce #(set/intersection %1 %2) (map #(ph/get-phoneme % :as-set) phonemes))))

(defn all-phonemes-with
  "Return a set of all phonemes in `inventory` with `features`.
  `features` is a hash map, `inventory` a seq of phonemes."
  [features inventory]
  (set (filter #(set/subset? (set features) (set (get ph/phonemes %))) (set inventory))))

(defn get-feature-value
  "Return the value of `feature` in `phoneme`.
  If `feature` does not exist in `phoneme`, return nil."
  [feature phoneme]
  (get (ph/get-phoneme phoneme) feature))

(defn has-feature?
  "Return true if `phoneme` has `feature`.
  `feature` should be a keyword or a keyword-value vector."
  [phoneme feature]
  (if (keyword? feature)
    (contains? (ph/get-phoneme phoneme) feature)
    (contains? (ph/get-phoneme phoneme :as-set) feature)))

(defn natural-class?
  "Determine if `phonemes` is a natural class given `inventory`.
  `phonemes` and `inventory` are seqs of phoneme strings, either IPA or X-SAMPA.
  The return value is a hash map of features describing the natural class or nil
  if the phonemes do not constitute a natural class."
  [phonemes inventory]
  (let [members (set (map ph/normalize phonemes))
        inv (map ph/normalize inventory)
        features (vec (shared-features members))
        max-n (count features)]
    (loop [n 1]
      (if (<= n max-n)
        (let [fs (combo/combinations features n)]
          (or (first (filter #(= members (all-phonemes-with % inv)) fs))
              (recur (inc n))))))))

(defn feature->string
  "Turn `feature` into a displayable string.
  `feature` should be a two-element vector of [feature-name value]. `conversion`
  is a hash map describing how the values `true` and `false` should be
  represented. Default is {true \"+\", false \"-\"}."
  ([feature] (feature->string {true "+" false "-"} feature))
  ([conversion feature]
   (if (ph/place-feature? (first feature))
     (if (second feature) (name (first feature)) "") ; Filter out place features with value `false`.
     (str (get conversion (second feature)) (name (first feature))))))

(defn describe-phoneme
  "Return a string describing `features`.
  `sep` is the separator, `conv` a hash map describing how the values `true`
  and/or `false` should be represented. Default is \"+\" for `true` and \"-\"
  for `false'."
  [features & {:keys [sep conv]}]
  (let [convertors (merge {true "+" false "-"} conv)]
    (string/join (or sep " ") (map #(feature->string convertors %) features))))

(def test-inventory ["d" "n" "œ"
                     "z" "s" "f"
                     "e" "ɦ" "p"
                     "j" "x" "v"
                     "ʃ" "ʏ" "ʔ"
                     "ɛ" "ç" "a"
                     "ɶ" "t" "ʊ"
                     "ɔ" "i" "k"
                     "b" "r" "y"
                     "g" "χ" "l"
                     "ŋ" "u" "ɪ"
                     "ø" "m" "o"])

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
