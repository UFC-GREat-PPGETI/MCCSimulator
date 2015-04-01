networkRandomGenerator <- function (number ,provider.localization.net.link){
 answerArray <-(1:number)
	for(n in 1:number){
		switch(provider.localization.net.link, 
			oi.pici.3g.down={
			mi.teo <- rcauchy(1, location = 134.66, scale = 5.3831) 
		},
		oi.pici.3g.up={
			library("fExtremes")
			mi.teo <- rgev(1, xi = -0.86253, mu = 28.825 , beta =3.7849)
		},
		oi.aero.3g.down={
			library("fExtremes")
			mi.teo <- rgev(1, xi = -0.741192, mu = 102.71 , beta = 66.094)
			
		},
		oi.aero.3g.up={
			mi.teo <- rcauchy(1, location = 31.908, scale = 0.04644)
		},
		claro.aero.3g.down={
			library("FAdist")
			mi.teo <-rlnorm3(1,shape=0.56169,scale=3.2526,thres=42.952)
		},
		claro.aero.3g.up={
			library("actuar")
			mi.teo <- rgamma(1, shape= 1.0597, scale = 45.735)
		},
		claro.pici.3g.down={
			mi.teo <- rcauchy(1, location = 66.939, scale = 13.488)
		},
		claro.pici.3g.up={
			library("fExtremes")
			mi.teo <- rgev(1, xi = 0, mu = 152.46 , beta = 11.409)
		},
		tim.aero.4g.down={
			library("VGAM")
			mi.teo <- rfrechet(1, shape = 6.0134, scale=948.6)
		},
		tim.aero.4g.up={
			library("FAdist")
			mi.teo <-rlnorm3(1,shape=0.76169,scale=4.6913,thres=637.98)
		},
		tim.pici.4g.down={
			mi.teo <- rcauchy(1, location = 205.33, scale = 21.05)
		},
		tim.pici.4g.up={
			library("statmod")
			mi.teo <- rinvgauss(1, mu=318.89, lambda=19498)
		},
		vivo.aero.4g.down={
			library("VGAM")
			mi.teo <- rfrechet(1, shape = 4.2778, scale=905.21)
		},
		vivo.aero.4g.up={
			mi.teo <- rchisq(1, df=368, ncp = 0)
		},
		vivo.pici.4g.down={
			mi.teo <- rcauchy(1, location = 1218.5, scale = 54.351)
		},
		vivo.pici.4g.up={
			mi.teo <- rcauchy(1, location = 40.786, scale = 20.202)
		}
		
	)
	answerArray[n]=mi.teo;
	}
	
 return(answerArray)
}


