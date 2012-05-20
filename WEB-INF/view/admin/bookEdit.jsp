<s:layout-render name="layout/default.jsp">
	<s:layout-component name="contents">
		<div>
			<s:form action="/admin/BookEdit.action" id="book-edit-form">
				<s:hidden name="book.bookId" />
          bookName  <s:text name="book.bookName" />
				<br />
          publicFlag  <s:checkbox name="book.publicFlag" value="1" />
				<br />
				<s:submit name="edit"></s:submit>
			</s:form>
			<s:link
				beanclass="com.studio4096.portfolio.action.admin.ImageEditActionBean"
				id="sys-image-select-button">
          image select button
          </s:link>
			<br />
			<s:form action="/admin/PageEdit.action" id="sys-add-page-form">
				<s:hidden name="page.bookId" value="${actionBean.book.bookId}" />
				<s:submit id="sys-add-page-button" name="newPage">add page.</s:submit>
			</s:form>
		</div>
		<div><a href="admin/CacheClear.action" target="_blank">clear cache</a></div>

		<div class="clearfix">
			<div id="sys-page-select-area">
				<ul id="sys-page-list">
					<c:forEach var="onePage" items="${actionBean.pageList}"
						varStatus="status">
						<li class="sys-each-page" id="sys-page-${onePage.pageId }">pageId:${onePage.pageId}
						<span class="sys-page-delete-button"> (x)</span>
						<input name="pageId" type="hidden" value="${onePage.pageId }" />
						</li>
					</c:forEach>
				</ul>
			</div>


			<div id="sys-page-edit-area">
				<%@ include file="pageEdit.jsp"%>
			</div>
		</div>
		<div id="sys-image-list">image list</div>
		<script type="text/javascript">
var $bookEditForm =$('#book-edit-form');
    var $imageSelectButton =$('#sys-image-select-button');
    var $addPageButton =$('#sys-add-page-button');
    var $addPageForm = $('#sys-add-page-form');
    var $pagesList = $('#sys-page-list');
    var $eachPage = $('.sys-each-page');
    var $imageList = $('#sys-image-list');
    var $image = null;
    var $pageEditView = $('#sys-page-edit-area');
    $pageEditView.copyProperty = function(name,eachPage){
        var v= eachPage.find('input[name='+name+']').val()
        this.find('input[name='+name+']').val(v);
    };
    
    $(function(){
        $pagesList.sortable({
            update: function(event, ui) {
                   var query=$(this).sortable('serialize',{key:'pageIds[]'});
                    $.post('/portfolio/admin/PageEdit.action','sort=&bookId='+ $bookEditForm.find('input[name=book\\.bookId]').val()+'&'+query)
                    .error(function(){alert('error:'+query);
                    });
               }	
        });
        var showPage =function(){
            $pageEditView.load('/portfolio/admin/PageEdit.action?pageId=' +$(this).find('input[name=pageId]').val());
           	return false;
         };
        $eachPage.click(showPage);
        $eachPage.find('.sys-page-delete-button').click(function(){
        	$this =$(this);
        	$listElement = $this.parent('li');
        	$.post('/portfolio/admin/PageEdit.action?deletePage=&page.pageId=' + $listElement.find('input[name=pageId]').val());
        	$listElement.remove();
        	
        	alert('deleted');
        });
        $imageSelectButton.click(function(){

        	var $this =$(this);
        	var url=$this.attr('href');
        	$this.attr('href','javascript:void(0);return false;');
            $imageList.dialog({ width:650,height: 400 ,title:'images'}).dialogExtend({
                'minimize' : true,
                'maximize' : true,
                'dblclick' : 'collapse',
                'events' : { "load" : function(evt, dlg) {$imageList.load(url);} }
            });
        	$this.attr('href',url);
        	return false;
        });
        $addPageForm.submit(function(){
        	$this =$(this);
        	var $newPage =null;
            $.post($this.attr( 'action' ),$this.serialize() + '&'+$addPageButton.attr('name') + "=")
            .success(function(html) {
            	$newPage=$(html);
            	$newPage.click(showPage);
                $pagesList.append($newPage);
                $eachPage =$pagesList.find('.sys-each-page');
            })
            .error(function(){alert('error:'+query);})
            ;
        	return false;
        });

    });
    </script>
	</s:layout-component>
</s:layout-render>
