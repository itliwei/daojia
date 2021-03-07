<template>
  <div id="app" mode="out-in">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>
<style scoped>
  .body-wrapper {
    padding: 20;
    min-width: 1000px;
  }

  .header-wrapper {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding-bottom: 20px;
    border-bottom: 1px solid #dcdfe6;
  }

  .select-wrapper {
    margin-bottom: 20px;
  }

  .console-wrapper {
    border: 1px solid #dcdfe6;
    border-radius: 4px;
  }

  .console {
    min-height: 600px;
    padding: 10px 0;
    background-color: black;
    color: #ffffff;

    font-family: Consolas, Monaco, Menlo, "Courier New", monospace !important;
    margin: 0;
  }
  .console-title {
    padding: 10px 20px;
    border-bottom: 1px solid #dcdfe6;
    background: #f0f2f5;
  }
  .command-history-wrapper {
    word-break: break-all;
    word-wrap: break-word;
  }
  .connected {
    margin-bottom: 10px;
  }

  .command-input >>> .el-input__inner,
  .command-input >>> .el-input-group__prepend {
    font-family: Consolas, Monaco, Menlo, "Courier New", monospace !important;
    border: none !important;
    font-size: 14px;
    background-color: #000000 !important;
    color: #ffffff !important;
    padding: 0;
  }

  .command-input >>> .el-input-group__prepend {
    padding-right: 5px;
  }

  .command-input >>> .el-input__inner:focus {
    outline: none;
  }
  .command-input >>> .el-input__inner ::-webkit-input-placeholder {
    caret-color: red;
  }

  .history {
    width: auto;
    line-height: 20px;
    word-break: normal;
    white-space: pre-wrap;
    word-wrap: break-word;
    font-family: Consolas, Monaco, Menlo, "Courier New", monospace !important;
    line-height: 24px;
  }

  .result-wrapper {
    height: auto;
    padding: 0;
    line-height: 20px;
    word-break: normal;
    white-space: pre-wrap;
    word-wrap: break-word;
  }

  .cm-s-seti.CodeMirror {
    background: #000000 !important;
  }
  .cm-s-seti .CodeMirror-activeline-background {
    background: #000000;
  }
  .cm-s-seti .CodeMirror-selected {
    background: #d7d4f0;
  }
  .cm-s-seti .CodeMirror-linenumber {
    color: #aeaeae;
  } /*行号数字*/
  .cm-s-seti .cm-quote {
    color: #090;
  } /*引号*/
  .cm-s-seti .cm-keyword {
    color: #3300cc;
  } /*关键字，例如：SELECT*/
  .cm-s-seti .cm-number {
    color: #333333;
  } /*数字*/
  .cm-s-seti .cm-variable-2 {
    color: #333333;
  } /*变量2，例如：a.id中的.id*/
  .cm-s-seti .cm-comment {
    color: #009933;
  } /*注释*/
  .cm-s-seti .cm-string {
    color: #009933;
  } /*字符串*/
  .cm-s-seti .cm-string-2 {
    color: #009933;
  } /*字符串*/
</style>
