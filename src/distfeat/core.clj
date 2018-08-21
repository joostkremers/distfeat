(ns distfeat.core
  (:require [clojure.set :as set]
            [clojure.string :as string]
            [distfeat.phonemes :as ph])
  (:gen-class))

(defn shared-features
  "Return a map of features and values shared by all elements in `phonemes`.
  `phonemes` should be a seq of sets of features."
  [& phonemes]
  (into {} (reduce #(set/intersection %1 %2) phonemes)))

(defn all-phonemes-with
  "Return all phonems with `features`."
  [features]
  (filter #(set/subset? (set features) (set (second %))) (set ph/phonemes)))

(defn get-phoneme
  "Return a map of features for `phoneme`.
  `phoneme` can be an IPA symbol or an X-SAMPA description. If `as-set` is true,
  return the features as a set, otherwise as a hash map."
  ([phoneme] (get-phoneme phoneme nil))
  ([phoneme as-set] (let [ph (ph/normalize phoneme)
                          features (get ph/phonemes ph)]
                      (if as-set
                        (set features)
                        features))))

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

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
