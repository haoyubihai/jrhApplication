package com.example.jrhapplication.utils

/**
 ***************************************
 * 项目名称:jrhApplication
 * @Author jiaruihua
 * 邮箱：jiaruihua@ksjgs.com
 * 创建时间: 2020/5/6     2:01 PM
 * 用途:
 ***************************************
 */
class JsonOutput {
    companion object {
        val OPEN_BRACKET :Char= '['
        val CLOSE_BRACKET:Char = ']'
        val OPEN_BRACE :Char= '{'
        val CLOSE_BRACE :Char= '}'
        val COLON :Char= ':'
        val COMMA:Char = ','
        val SPACE :Char= ' '
        val NEW_LINE :Char= '\n'
        val QUOTE :Char= '"'
        val EMPTY_STRING_CHARS = charArrayOf('"', '"')
        val EMPTY_MAP_CHARS = charArrayOf('{', '}')
        val EMPTY_LIST_CHARS = charArrayOf('[', ']')
    }


}