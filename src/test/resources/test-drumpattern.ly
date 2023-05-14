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
        title = "My first test drum pattern"
        tagline = ""
    }
    \markup \vspace #2
    \score {
        \new Staff {
            \time 4/4
            \new Voice {
            }
        }

        \layout { }

        \midi { }
    }
}
