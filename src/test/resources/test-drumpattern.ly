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
                \relative c'' {
                    a1
                    a2 a2
                    a4 a4 a4 a4
                    a8 a8 a8 a8 a8 a8 a8 a8
                    \break
                    r1
                    r2 r2
                    r4 r4 r4 r4
                    r8 r8 r8 r8 r8 r8 r8 r8
                    \break
                }
            }
        }

        \layout { }

        \midi { }
    }
}
