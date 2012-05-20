var $4096=$4096?$4096:{cfg:{ctx:'/portfolio'}}
$4096.url={resize:$4096.cfg.ctx+'/ScreenResize.action'}
$4096.show=function(e,v){e.style.visibility=v?'visible':'hidden';}
$4096.each=function(a,f){for(i=0;i<a.length;i++)f(a[i],i);}
$4096.addToggle=function(dom){
	dom.opened=false;
	dom.toggle=function(inout,op,f){
		d=this;d.opened=!d.opened;
		if(d.opened){
			inout='in';op=1;f=function(){};d.style.display='block';d.style.visibility = 'visible';
		}else{
			pos=_.P(d);inout='out';op=0;f=function(){d.style.visibility='hidden';d.style.width=pos.w+'px';d.style.height=pos.h+'px';}
		}
		_.F(inout, d);_.slide(op, d,f);
    }
	return dom;
}
_.R(function(){
	$4096.cl={d:document,de:document.documentElement,bd:document.body,cm:document.compatMode}
	$4096.cl.noBackCompat=($4096.cl.cm&&($4096.cl.cm!='BackCompat'));
	$4096.cl.vA=($4096.cl.de?$4096.cl.de:$4096.cl.bd);//viewAll
	$4096.cl.v=($4096.cl.noBackCompat?$4096.cl.de:$4096.cl.bd);//view
	$4096.cl.getWA=function(){return ($4096.cl.vA.scrollWidth?$4096.cl.vA:$4096.cl.bd).scrollWidth;}
	$4096.cl.getHA=function(){return (($4096.cl.vA.scrollHeight?$4096.cl.vA:$4096.cl.bd).scrollHeight);}
	$4096.cl.getW=function(){return ($4096.cl.v.clientWidth);}
	$4096.cl.getH=function(){return ($4096.cl.v.clientHeight);}
	$4096.contents=_.C('contentsArea',_.G('wrapper'));
    var timer2 = null;
    $4096.sendSizes =  function(q){
        _.X($4096.url.resize+'?'+_.Q(q),function(res,x){
        	r=_.S(res,true);$4096.each($4096.contents,function(e,i){
        		s=e.style;
        		s.width=r.w+'px';s.height=r.h+'px';
        		s.marginLeft=-(r.w/2)+'px';s.marginTop=-(r.h/2)+'px';
        	});
        });
    }
    $4096.resizeWindow =  function(){
        clearTimeout(timer2);
        timer2= setTimeout(function() {
           clearTimeout(timer2);
           q={"v.w":$4096.cl.getW(),"v.h":$4096.cl.getH(),"vA.w":$4096.cl.getWA(),"vA.h":$4096.cl.getHA()};
           $4096.sendSizes(q);
        },300);
    };
    _.E(window,'resize',$4096.resizeWindow);
});
