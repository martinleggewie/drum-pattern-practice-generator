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
        title = "2023-05-20"
        tagline = ""
    }
    \markup \vspace #2
    \score {
        \new Staff {
            \time 4/4
            \new Voice {
                \relative c'' {
                    r2 a2
                    r32 a2 r8 r16 r16 r8 r32 a64 a32 r64
                    a8 r32 r4 a4 r32 a32 r4 a64 a64
                    r8 r16 r8 r64 r64 a32 a4 r4 a16 a32 a64 a64
                    \break
                    r64 r8 r2 r8 r64 a8 r64 r16 a64
                    a1
                    r2 a16 a8 a4 a32 r32
                    a64 r2 r32 a16 a64 r32 a8 r8 a64 r32 a64 a32
                    \break
                    a1
                    r1
                    r4 r16 r8 a2 a64 r64 a32
                    r2 a8 r16 a4 r16
                    \break
                    a8 a32 a8 r16 a64 a2 r8 a64
                    r32 a64 r4 r2 a8 r16 r64
                    a32 a4 a32 r8 a4 a8 a64 r64 a16 r64 r16 a64
                    r16 r8 a8 r2 r16 a8
                    \break
                }
            }
        }

        \layout { }

        \midi { }
    }
}
