(ns distfeat.phonemes)

(def obstruents-table
  [[      :cons  :son   :appr  :syl   :voice :asp   :glot  :cont  :nasal :lat   :sibil :LAB   :round :COR   :ant   :apic  :DORS  :back  :high  :low   :RAD]
   ["p"   true   false  false  false  false  false  false  false  false  false  false  true   false  nil    nil    nil    nil    nil    nil    nil    nil ]
   ["b"   true   false  false  false  true   false  false  false  false  false  false  true   false  nil    nil    nil    nil    nil    nil    nil    nil ]
   ["t"   true   false  false  false  false  false  false  false  false  false  false  nil    nil    true   true   true   nil    nil    nil    nil    nil ]
   ["d"   true   false  false  false  true   false  false  false  false  false  false  nil    nil    true   true   true   nil    nil    nil    nil    nil ]
   ["t`"  true   false  false  false  false  false  false  false  false  false  false  nil    nil    true   false  true   nil    nil    nil    nil    nil ]
   ["d`"  true   false  false  false  true   false  false  false  false  false  false  nil    nil    true   false  true   nil    nil    nil    nil    nil ]
   ["c"   true   false  false  false  false  false  false  false  false  false  false  nil    nil    true   false  false  nil    nil    nil    nil    nil ]
   ["J\\" true   false  false  false  true   false  false  false  false  false  false  nil    nil    true   false  false  nil    nil    nil    nil    nil ]
   ["k"   true   false  false  false  false  false  false  false  false  false  false  nil    nil    nil    nil    nil    true   true   true   false  nil ]
   ["g"   true   false  false  false  true   false  false  false  false  false  false  nil    nil    nil    nil    nil    true   true   true   false  nil ]
   ["q"   true   false  false  false  false  false  false  false  false  false  false  nil    nil    nil    nil    nil    true   true   false  false  nil ]
   ["G"   true   false  false  false  true   false  false  false  false  false  false  nil    nil    nil    nil    nil    true   true   false  false  nil ]
   ["f"   true   false  false  false  false  false  false  true   false  false  false  true   false  nil    nil    nil    nil    nil    nil    nil    nil ]
   ["v"   true   false  false  false  true   false  false  true   false  false  false  true   false  nil    nil    nil    nil    nil    nil    nil    nil ]
   ["T"   true   false  false  false  false  false  false  true   false  false  false  nil    nil    true   true   false  nil    nil    nil    nil    nil ]
   ["D"   true   false  false  false  true   false  false  true   false  false  false  nil    nil    true   true   false  nil    nil    nil    nil    nil ]
   ["s"   true   false  false  false  false  false  false  true   false  false  true   nil    nil    true   true   true   nil    nil    nil    nil    nil ]
   ["z"   true   false  false  false  true   false  false  true   false  false  true   nil    nil    true   true   true   nil    nil    nil    nil    nil ]
   ["S"   true   false  false  false  false  false  false  true   false  false  true   nil    nil    true   false  false  nil    nil    nil    nil    nil ]
   ["Z"   true   false  false  false  true   false  false  true   false  false  true   nil    nil    true   false  false  nil    nil    nil    nil    nil ]
   ["C"   true   false  false  false  false  false  false  true   false  false  false  nil    nil    nil    nil    nil    true   false  true   false  nil ]
   ["j\\" true   false  false  false  true   false  false  true   false  false  false  nil    nil    nil    nil    nil    true   false  true   false  nil ]
   ["x"   true   false  false  false  false  false  false  true   false  false  false  nil    nil    nil    nil    nil    true   true   true   false  nil ]
   ["G"   true   false  false  false  true   false  false  true   false  false  false  nil    nil    nil    nil    nil    true   true   true   false  nil ]
   ["X"   true   false  false  false  false  false  false  true   false  false  false  nil    nil    nil    nil    nil    true   true   false  false  nil ]
   ["R"   true   false  false  false  true   false  false  true   false  false  false  nil    nil    nil    nil    nil    true   true   false  false  nil ]
   ["X\\" true   false  false  false  false  false  false  true   false  false  false  nil    nil    nil    nil    nil    nil    nil    nil    nil    true]
   ["?\\" true   false  false  false  true   false  false  true   false  false  false  nil    nil    nil    nil    nil    nil    nil    nil    nil    true]])

(def sonorants-table
  [[      :cons  :son   :appr  :syl   :voice :asp   :glot  :cont  :nasal :lat   :sibil :LAB  :round :COR   :ant   :apic  :DORS  :back  :high  :low ]
   ["m"   true   true   false  false  true   false  false  false  true   false  false  true  false  nil    nil    nil    nil    nil    nil    nil  ]
   ["n"   true   true   false  false  true   false  false  false  true   false  false  nil   nil    true   true   true   nil    nil    nil    nil  ]
   ["n`"  true   true   false  false  true   false  false  false  true   false  false  nil   nil    true   false  true   nil    nil    nil    nil  ]
   ["J"   true   true   false  false  true   false  false  false  true   false  false  nil   nil    true   false  false  nil    nil    nil    nil  ]
   ["N"   true   true   false  false  true   false  false  false  true   false  false  nil   nil    nil    nil    nil    true   true   true   false]
   ["l"   true   true   true   false  true   false  false  false  false  true   false  nil   nil    true   true   true   nil    nil    nil    nil  ]
   ["l`"  true   true   true   false  true   false  false  false  false  true   false  nil   nil    true   false  true   nil    nil    nil    nil  ]
   ["L"   true   true   true   false  true   false  false  false  false  true   false  nil   nil    true   false  false  nil    nil    nil    nil  ]
   ["r"   true   true   false  false  true   false  false  true   false  false  false  nil   nil    true   true   true   nil    nil    nil    nil  ]
   ["r\\" true   true   true   false  true   false  false  true   false  false  false  nil   nil    true   true   true   nil    nil    nil    nil  ]
   ["4"   true   true   false  false  true   false  false  false  false  false  false  nil   nil    true   true   true   nil    nil    nil    nil  ]
   ["j"   false  true   true   false  true   false  false  true   false  false  false  nil   nil    true   false  false  nil    nil    nil    nil  ]
   ["w"   false  true   true   false  true   false  false  true   false  false  false  true  true   nil    nil    nil    true   true   true   false]
   ["h"   false  false  false  false  false  true   false  true   false  false  false  nil   nil    nil    nil    nil    nil    nil    nil    nil  ]
   ["h\\" false  false  false  false  true   true   false  true   false  false  false  nil   nil    nil    nil    nil    nil    nil    nil    nil  ]
   ["?"   false  false  false  false  false  false  true   false  false  false  false  nil   nil    nil    nil    nil    nil    nil    nil    nil  ]])

(def vowels-table
  [[    :cons  :son  :appr :syl  :voice :cont :DORS :back  :high  :low   :tense :LAB  :round]
   ["i" false  true  true  true  true   true  true  false  true   false  true   nil   nil   ]
   ["I" false  true  true  true  true   true  true  false  true   false  false  nil   nil   ]
   ["y" false  true  true  true  true   true  true  false  true   false  true   true  true  ]
   ["Y" false  true  true  true  true   true  true  false  true   false  false  true  true  ]
   ["e" false  true  true  true  true   true  true  false  false  false  true   nil   nil   ]
   ["E" false  true  true  true  true   true  true  false  false  false  false  nil   nil   ]
   ["2" false  true  true  true  true   true  true  false  false  false  true   true  true  ]
   ["9" false  true  true  true  true   true  true  false  false  false  true   true  true  ]
   ["{" false  true  true  true  true   true  true  false  false  true   false  nil   nil   ]
   ["a" false  true  true  true  true   true  true  false  false  true   false  nil   nil   ]
   ["&" false  true  true  true  true   true  true  false  false  true   false  true  true  ]
   ["u" false  true  true  true  true   true  true  true   true   false  true   true  true  ]
   ["U" false  true  true  true  true   true  true  true   true   false  false  true  true  ]
   ["M" false  true  true  true  true   true  true  true   true   false  false  nil   nil   ]
   ["o" false  true  true  true  true   true  true  true   false  false  true   true  true  ]
   ["O" false  true  true  true  true   true  true  true   false  false  false  true  true  ]
   ["A" false  true  true  true  true   true  true  true   false  true   false  nil   nil   ]])

(def ipa-symbols {"p" "p"   ,  "m" "m"    ,  "i" "i"
                  "b" "b"   ,  "n" "n"    ,  "ɪ" "I"
                  "t" "t"   ,  "ɳ" "n`"   ,  "y" "y"
                  "d" "d"   ,  "ɲ" "J"    ,  "ʏ" "Y"
                  "ʈ" "t`"  ,  "ŋ" "N"    ,  "e" "e"
                  "ɖ" "d`"  ,  "l" "l"    ,  "ɛ" "E"
                  "c" "c"   ,  "ɭ" "l`"   ,  "ø" "2"
                  "ɟ" "J\\" ,  "ʎ" "L"    ,  "œ" "9"
                  "k" "k"   ,  "r" "r"    ,  "æ" "{"
                  "ɡ" "g"   ,  "ɹ" "r\\"  ,  "a" "a"
                  "q" "q"   ,  "ɾ" "4"    ,  "ɶ" "&"
                  "ɢ" "G"   ,  "j" "j"    ,  "u" "u"
                  "f" "f"   ,  "w" "w"    ,  "ʊ" "U"
                  "v" "v"   ,  "h" "h"    ,  "ɯ" "M"
                  "θ" "T"   ,  "ɦ" "h\\"  ,  "o" "o"
                  "ð" "D"   ,  "ʔ" "?"    ,  "ɔ" "O"
                  "s" "s"                    "ɑ" "A"
                  "z" "z"
                  "ʃ" "S"
                  "ʒ" "Z"
                  "ç" "C"
                  "ʝ" "j\\"
                  "x" "x"
                  "ɣ" "G"
                  "χ" "X"
                  "ʁ" "R"
                  "ħ" "X\\"
                  "ʕ" "?\\"})

(def xsampa-symbols (clojure.set/map-invert ipa-symbols))

(defn ipa->xsampa
  "Return the X-SAMPA representation for the IPA symbol `p`."
  [p]
  (get ipa-symbols p))

(defn xsampa->ipa
  "Return the IPA symbol for the X-SAMPA representation `x`."
  [x]
  (get xsampa-symbols x))

(defn normalize
  "Turn `phoneme` into its X-SAMPA representation.
  If `phoneme` is already an X-SAMPA representation, just return it."
  [phoneme]
  (or (ipa->xsampa phoneme) phoneme))

(def features [:cons :son :appr :syl         ; major class features
               :voice :asp :glot             ; laryngeal features
               :cont :nasal :lat :sibil      ; manner features
               :LAB :round                   ; place features
               :COR :ant :apic
               :DORS :back :high :low :tense
               :RAD])

(def place-features {:LAB  #{:round}
                     :COR  #{:ant :apic}
                     :DORS #{:back :high :tense}
                     :RAD  #{}})

(defn place-feature?
  "Return true if `feature` is a place feature.
  The actual return value is the set of dependent features."
  [feature]
  (get place-features feature))

(defn convert-table-to-map
  "Convert a feature table to a map."
  [[features & phonemes]]
  (into {} (map (fn [[phoneme & values]]
                  (vector phoneme (into {} (filter #(not (nil? (second %)))
                                                   (map vector features values)))))
                phonemes)))

(def obstruents (convert-table-to-map obstruents-table))
(def sonorants (convert-table-to-map sonorants-table))
(def vowels (convert-table-to-map vowels-table))
(def phonemes (merge obstruents sonorants vowels))

(defn get-phoneme
  "Return a map of features for `phoneme`.
  `phoneme` can be an IPA symbol or an X-SAMPA description. If `as-set` is true,
  return the features as a set, otherwise as a hash map."
  ([phoneme] (get-phoneme phoneme nil))
  ([phoneme as-set] (let [ph (normalize phoneme)
                          features (get phonemes ph)]
                      (if as-set
                        (set features)
                        features))))
