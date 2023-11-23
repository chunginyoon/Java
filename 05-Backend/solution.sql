#1
use world;

#2
desc city;
desc country;
desc countrylanguage;

#3
select code, name, continent, region
from country
where code='KOR';

#4
select code, name, gnp , gnpold, (gnp-gnpold) as "gnp변동량"
from country
where (gnp-gnpold)>0
order by `gnp변동량`;

#5
select distinct continent
from country
order by length(continent);

#6
select concat(name, '은 ', region, '에 속하며 인구는 ', population, '명이다.') as "정보"
from country
order by name;

#7
select name, continent, gnp, population
from country
where IndepYear is null and population >= 10000
order by population;

#8
select code, name, Population
from country
where Population between 100000000 and 200000000
order by Population desc
limit 3;

#9
select code, name, indepyear
from country
where indepyear in (800, 1810, 1811, 1901)
order by indepyear, code;

#10
select code, name, region
from country
where region like '%asia%' and name like '_o%';

#11
select round(length('홍길동')/3,0) as "한글", length('hong') as "영문";

#12
select code, name, governmentform
from country
where governmentform like '%republic%' and length(name) >=10
order by length(name) desc
limit 10;

#13
select code, name
from country
where SUBSTR(code, 1, 1) in ('a', 'e', 'i', 'o', 'u')
order by name
limit 2,3;

#14
select name, concat(substr(name, 1, 2), lpad('*', length(name)-4, '*'),substr(name, -2)) as guess
from country;

#15
select distinct replace(region,' ', '_') as "지역들"
from country
order by length(`지역들`) desc;

#16
select name, surfacearea, population, round(surfacearea/population,3) as "1인당 점유면적"
from country
where population >= 100000000
order by `1인당 점유면적`;
