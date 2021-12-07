Code	challenge
# CRYPTO	WALLET	PERFORMANCE
## 1. OBJECTIVE
Your	assignment	is	to	implement	a	Java	program	that	given	a	collection	of	crypto	assets	
with	their	positions,	it	must	retrieve,	concurrently, their	latest	prices	from	the	Coincap	
API	and	return	the	updated	total	financial	value	of	the	wallet with	performance	data.

Input
CSV	file	representing the	wallet	with	columns	symbol,	quantity,	price

Output
Print	a	line	with
total={},best_asset={},best_performance={},worst_asset={},worst_performance={}

Where
- total:	total	financial	value	in	USD	of	the	entire	wallet
- best_asset:	which	asset	had	the	best	performance	(value	increase)	from the input	CSV	compared	to	the	latest	price	retrieved	from	the	API
- best_performance:	percentage	of	the	performance	of	the	best_asset
- worst_asset:	which	asset	had	the	worst	performance	(value	decrease)	from the	input	CSV	compared	to	the	latest	price	retrieved	from	the	API
- worst_performance:	percentage	of	the	performance	of	the	worst_asset
- Values	rounded	to	2	decimal	places,	HALF_UP

## 2. TECHNICAL	BRIEFING
Required	environment,	tools	and	languages:
- Java	11+	
- Build	the	project	with	maven
- Write	your	code	in	English
- Read	the	CSV	from	src/main/resources and	there	is	no	need	to	validate	the	CSV, consider	it	will	always	be	valid
- Feel	free	to	use	any	additional	Java	framework	or	libraries	you	want. Mandatory	technical	requirements:
- Write	unit	tests,	it’s	up	to	you	to	mock	or	not	the	API	for	the	unit	tests
- Retrieve	the	prices	simultaneously	by	groups	of	3	assets	concurrently,	i.e.,	at	any	point,	at	most	3 threads	will	be	active	processing	tasks – but	never singlethreaded (unless	there	is	only	one	asset	in	the	wallet).	For	example,	if	you	process	a	wallet	with	more	than	3	assets,	and	each	API	call	takes	10s,	your	code should	log	something	like: Now is 10:00:00
Submitted request ASSET_A at 10:00:01
Submitted request ASSET_B at 10:00:01
Submitted request ASSET_C at 10:00:01

(program hangs, waiting for some of the previous requests
to finish)

Submitted request ASSET_D at 10:00:11

...
Note:	as	you	need	to	return	the	total	value	of	the	wallet,	your	program	will	have	
to	wait	until	all	assets	have	been	processed.

- Use	Coincap	API:	https://docs.coincap.io/ o Assets:	https://docs.coincap.io/#89deffa0-ab03-4e0a-8d92-637a857d2c91
§ Use	the	Assets	API	to	lookup asset	id	by	symbol
o Assets	history price	on	specific	date:	
https://docs.coincap.io/#61e708a8-8876-4fb2-a418-86f12f308978
§ Use	the	following	parameters	so	we	can	test	with	the	same	data:
interval=d1&start=1617753600000&end=161
7753601000

## 3. EXAMPLE
- GIVEN
- - symbol,quantity,price
- - BTC,0.12345,37870.5058
- - ETH,4.89532,2004.9774
- WHEN
3
o Using	Coincap	Assets	API,	we	get	the	asset	ids	“bitcoin”	and	“ethereum" respectively o Next,	using	the	Coincap Assets	History	API,	we	get	the	prices	“56999.9728252053067291”	and	“2032.1394325557042107”	respectively
- THEN
Current	BTC	position	is	0.12345	*	56999.9728252053067291
Current	ETH	position	is	4.89532	*	2032.1394325557042107
Total	=	Current	BTC	Position	+	Current	ETH	Position
BTC	had	an	increase	of	150% compared	to	original	price	of	“37870.5058” and	
ETH	an	increase	of	101%
total=16984.62,best_asset=BTC,best_performance=1.51,worst_asse
t=ETH,worst_performance=1.01

## 4. DELIVERABLES
Please	upload	your	code	to	a	remote	git	repo	and	send	the	link.	Alternatively,	send	us	a	
zip	file	with	the	code.
