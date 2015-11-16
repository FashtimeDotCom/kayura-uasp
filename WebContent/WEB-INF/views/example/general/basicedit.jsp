<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<e:section name="title">基本列表</e:section>

<e:section name="head">
	<script type="text/javascript">

		$(function() {
			
		});
		

		function closeWindow() {
 			juasp.confirm("是否关闭？", function(r) {
 				if(r) { juasp.closeWin({ value : 1 }); }
 			});
		}
		
	</script>
</e:section>

<e:section name="body">

	<p>表单编辑页面</p>
	<div style="margin:20px 0;"></div>

	<e:linkbutton onclick="closeWindow();">关闭窗口</e:linkbutton>

</e:section>

<e:section name="code">
<pre><code class="html">

</code></pre>
</e:section>

<%@ include file="../shared/_content.jsp" %>