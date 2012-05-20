<s:layout-render name="/WEB-INF/view/layout/default.jsp">
    <s:layout-component name="html_head">
    <style>
    #contact-area{margin:5%;}
    </style>
    </s:layout-component>
    <s:layout-component name="contents">
        <div id="contact-area" >
            <h3>studio 4096</h3>
            <p>ご意見・ご感想・依頼・バグ報告等、twitter/メールでお気軽にお問い合わせください。</p>
            <p>今のところプログラム／デザイン／イラストレーションとも城島丈夫が一人でやっています。</p>
            <p>twitter:<a href="http://twitter.com/studio4096/" target="_blank">studio4096</a></p>
            <p>e-mail:<span id="mail-address"><a href=""></a></span></p>
        </div>
    </s:layout-component>
    <s:layout-component name="scripts">
    <script type="text/javascript">
    _.R(function(){
    	a=_.id('mail-address').getElementsByTagName('a')[0];
    	splitedAddress = ${mailAddress};
    	a.href='mailto:' + splitedAddress;
    	a.innerHTML=splitedAddress;
    });
    </script>
    </s:layout-component>
    
</s:layout-render>
