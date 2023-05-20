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
        title = "40 bars, 4 bars per line, quarter"
        tagline = ""
    }
    \markup \vspace #2
    \score {
        \new Staff {
            \time 4/4
            \new Voice {
                \relative c'' {
                    a4 a4 r4 a4
                    r4 a4 a4 a4
                    r4 r4 a4 a4
                    r4 r4 r4 a4
                    \break
                    r4 a4 a4 r4
                    r4 a4 a4 r4
                    a4 a4 r4 r4
                    r4 a4 a4 a4
                    \break
                    r4 a4 r4 a4
                    r4 r4 a4 a4
                    r4 r4 a4 r4
                    r4 a4 r4 a4
                    \break
                    a4 r4 a4 r4
                    a4 a4 a4 r4
                    r4 a4 r4 a4
                    a4 a4 r4 a4
                    \break
                    r4 r4 a4 a4
                    r4 a4 r4 r4
                    r4 a4 a4 a4
                    r4 r4 r4 r4
                    \break
                    a4 a4 r4 a4
                    r4 a4 a4 r4
                    r4 r4 a4 r4
                    a4 a4 r4 a4
                    \break
                    r4 a4 a4 r4
                    r4 a4 a4 a4
                    a4 a4 r4 a4
                    a4 r4 r4 a4
                    \break
                    r4 r4 a4 a4
                    a4 a4 r4 r4
                    r4 r4 a4 a4
                    r4 a4 a4 r4
                    \break
                    a4 r4 a4 a4
                    a4 a4 r4 r4
                    r4 r4 a4 r4
                    a4 a4 a4 r4
                    \break
                    a4 r4 a4 r4
                    r4 a4 a4 r4
                    r4 a4 r4 a4
                    r4 r4 r4 r4
                    \break
                }
            }
        }

        \layout { }

        \midi { }
    }
}
