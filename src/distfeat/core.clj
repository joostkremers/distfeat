(ns distfeat.core
  (:require [clojure.set :as set]
            [distfeat.phonemes :as ph])
  (:gen-class))

(defn shared-features
  "Return a map of features and values shared by all elements in `phonemes`."
  [& phonemes]
  (into {} (reduce #(set/intersection %1 %2) phonemes)))

(defn get-phoneme
  "Return a map of features for `phoneme`.
  `phoneme` can be an IPA symbol or an X-SAMPA description. If `as-set` is true,
  return the features as a set, otherwise as a hash map."
  ([phoneme] (get-phoneme phoneme nil))
  ([phoneme as-set] (let [p (or (get ph/xsampa phoneme) phoneme)
                          features (or (get ph/obstruents p)
                                       (get ph/sonorants p)
                                       (get ph/vowels p))]
                      (if as-set
                        (set features)
                        features))))

(defn describe-phoneme
  "Return a string describing `phoneme`."
  [phoneme]
  )

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
