<s:layout-render name="/WEB-INF/view/layout/default.jsp">
    <s:layout-component name="contents">
      <img src="${ctx }/images/full/${actionBean.imageData.imagePath }${actionBean.imageData.fileName }" alt="" class="Full"/>
      <div id="detailInfo">
        <div><span id="detailCloseButton">close</span></div>
        <table >
            <tr><th>title</th><td>${actionBean.imageData.title}</td> </tr>
            <tr><th>size</th><td>${actionBean.imageData.realSize}</td> </tr>
            <tr><th>media</th><td>${actionBean.imageData.media}</td> </tr>
            <tr><td colspan="2">${actionBean.imageData.description}</td> </tr>
        </table>
      </div>
    </s:layout-component>
    <s:layout-component name="controlPanel">
    <div id="controlPanel"  class="contentsArea">
      <div class="clearfix" >
        <span id="sys-moreIcon">more detail</span>
      </div>
      <c:if test="${actionBean.prevPage != null}">
      <div id="sys-prevLink" class="sys-pagingLink"><a href="${ctx }/show/${actionBean.prevPage.bookId}/${actionBean.prevPage.pageId}.html" >Preview</a></div>
      </c:if>
      <c:if test="${actionBean.nextPage != null}">
      <div id="sys-nextLink" class="sys-pagingLink"><a href="${ctx }/show/${actionBean.nextPage.bookId}/${actionBean.nextPage.pageId}.html" >Next</a></div>
      </c:if>
    </div>
    </s:layout-component>
    <s:layout-component name="scripts">
        <script type="text/javascript">
    /** show control panel when mouse moved. */
    var page={}
    page.contentsArea = _.C('contentsArea',_.G('wrapper'));
    page.cp = _.G('controlPanel');
    page.cp.stop=false;
    page.cp.show =function(){
        this.style.filter='alpha(opacity=100)';
        this.style.opacity='1.0';
        this.style.display= 'block';
    }
    page.info = $4096.addToggle(_.G('detailInfo'));
    var moreIcon = _.G('sys-moreIcon');
    var timer = null;
    page.showP=function () {
    	cp=page.cp;
    	if(!cp.stop){
    		cp.stop=true;
    		cp.show();
	    }else{
	       clearTimeout(timer);
	    }
		timer= setTimeout(function() {
		   clearTimeout(timer);
		   cp.stop=false;
		   _.F('out',cp);
		},1000);
	}
    $4096.each(page.contentsArea,function(e,i){_.on(e,'mousemove',page.showP);});
    _.E(moreIcon,'click',function(){$4096.show(moreIcon,false);page.info.toggle();});
    _.E(_.G('detailCloseButton'),'click',function(){page.info.toggle();$4096.show(moreIcon,true); });
    </script>
    </s:layout-component>
</s:layout-render>
