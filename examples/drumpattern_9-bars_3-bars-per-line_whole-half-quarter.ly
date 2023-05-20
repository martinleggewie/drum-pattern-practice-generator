\version "2.24.1"

\paper {
    indent = 0
    top-margin = 1\cm
    left-margin = 3\cm
    right-margin = 3\cm
    ragged-right = ##f
}

\layout {
    \context {
        \Score
        \remove "Bar_number_engraver"
    }
}

\book {
    \header {
        title = "9 bars, 3 bars per line, whole/half/quarter"
        tagline = ""
    }
    \markup \vspace #2
    \score {
        \new Staff {
            \time 4/4
            \new Voice {
                \relative c'' {
                    r1
                    a1
                    a1
                    \break
                    a2 r4 r4
                    a1
                    a1
                    \break
                    a4 a2 r4
                    r2 r2
                    a2 a4 a4
                    \break
                }
            }
        }

        \layout { }

        \midi { }
    }
}
