package dsl1

import kotlinx.html.a
import kotlinx.html.body
import kotlinx.html.div
import kotlinx.html.html
import kotlinx.html.stream.appendHTML

fun main() {
    System.out.appendHTML().
    html {
        body {
            div {
                a {
                    href = "epidemicsound.com"
                }
            }
        }
    }
}
