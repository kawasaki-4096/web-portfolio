      <c:forEach var="oneImage" items="${actionBean.imageList }">
        <div class="grid200px border01 sys-image-data" >
          <s:form action="/admin/ImageEdit.action" >
          <s:text name="imageData.imageId" value="${oneImage.imageId }"/>
            title  <s:text name="imageData.title" value="${oneImage.title }"/><br />
            realSize  <s:text name="imageData.realSize" value="${oneImage.realSize }"/><br />
            media  <s:text name="imageData.media" value="${oneImage.media }"/><br />
            description <s:textarea name="imageData.description">${oneImage.description }</s:textarea><br />
            <input type="hidden" name="imageFullPath" value="images/full${oneImage.imagePath }/${oneImage.fileName}"/>
            <img src="${ctx }/images/thumbs${oneImage.imagePath }/${oneImage.thumbnailName}" style="width:141px;height:100px;"/>
            <s:file name="imageFile" />
            <s:submit name="edit"></s:submit>
          </s:form>
        </div>
      </c:forEach>

    <div class="grid200px border01" >
      <s:form action="/admin/ImageEdit.action" >
        title  <s:text name="imageData.title" value="${oneImage.title }"/><br />
        realSize  <s:text name="imageData.realSize" value="${oneImage.realSize }"/><br />
        media  <s:text name="imageData.media" value="${oneImage.media }"/><br />
        description <s:textarea name="imageData.description">${oneImage.description }</s:textarea><br />
        <s:file name="imageFile" />
        <s:submit name="edit"></s:submit>
      </s:form>
    </div>
<script type="text/javascript">
$image = $('.sys-image-data');
$image.draggable({
    revert: 'invalid',
    helper:'clone',
    appendTo: 'body',
    containment: 'DOM',
    zIndex: 1500,
    addClasses: false,
    start: function(event, ui) {
    	var $artBoard = $('#sys-art-board');
    	$artBoard.droppable({
    			drop: function( event, ui ) {
    				var $this=$(this);
    				var $pageEditArea=$this.parent('#sys-page-edit-area');
    				var $mainVidual = $('#sys-main-vidual');
     				if($mainVidual.size() <= 0){
     					$mainVidual=$('<img src="" id="sys-main-vidual"/>');
    					$mainVidual.appendTo($artBoard);
     				}
    				$mainVidual.attr('src',ui.draggable.find('input[name=imageFullPath]').val());
    				var imageId =ui.draggable.find('input[name=imageData\\.imageId]').val();
    				var pageId= $pageEditArea.find('input[name=pageId]').val();
    				var $pageEdtForm =$('#sys-page-edit-form');
    				$.post($pageEdtForm.attr('action'),$pageEdtForm.serialize() +'&page.pageId='+pageId+'&page.imageId='+imageId+'&setImage=')
    				.success(function(){alert('image updated.');})
    				;
    			}
    	}); 
    }    
});
</script>
