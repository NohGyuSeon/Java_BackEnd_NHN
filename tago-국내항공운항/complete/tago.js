const SERVICE_KEY = "oqWzAGWsxHR/cre4r5C2TJD0qw9ldrsGzxIAnDQRIvb31Gt6m/EDMUhczdZ5gIFINhj/QBbAVRTnFuTnBMOJyw=="
//공항 조회
function getAirportList(){

    let url = "http://apis.data.go.kr/1613000/DmstcFlightNvgInfoService/getArprtList";
        url+="?serviceKey="  + encodeURIComponent(SERVICE_KEY);

        const promise = fetch(url,{method:'GET'})
        .then(response => response.text())
        .then(data => new window.DOMParser().parseFromString(data, "text/xml"))
        .then(xml => {
            const body = xml.getElementsByTagName("body")
            const items = body[0].getElementsByTagName("item");
            const airportList = new Array();
            for(let i=0; i<items.length; i++){
                 const airportId = items[i].getElementsByTagName("airportId")[0].innerHTML;
                 const airportNm = items[i].getElementsByTagName("airportNm")[0].innerHTML;

                 const airPortItem = {
                    airportId : airportId,
                    airportNm : airportNm
                 };
                 airportList.push(airPortItem);
            }
            return airportList
        });
        
    return promise;
}

//항공사 목록
function getAirlineList(){
    var url = 'http://apis.data.go.kr/1613000/DmstcFlightNvgInfoService/getAirmanList'; /*URL*/
    var queryParams = '?' + encodeURIComponent('serviceKey') + '='+SERVICE_KEY; /*Service Key*/
    queryParams += '&' + encodeURIComponent('_type') + '=' + encodeURIComponent('json'); /**/
    url+=queryParams;

    const promise = fetch(url,{method:'GET'})
        .then((response)=>{
            return response.json();
        }).then((json)=>{
            const airlineList = [];
            const items = json.response.body.items.item;

            for(let i=0; i<items.length; i++ ){
                const airlineItem = {
                    airlineId : items[i].airlineId,
                    airlineNm : items[i].airlineNm
                };
                airlineList.push(airlineItem);
            }

            return airlineList;
        });
        return promise;
}

/* 
 * @param {*} depAirportId  출발공항 아이디
 * @param {*} arrAirportId  도착공항 아이디
 * @param {*} depPlandTime  출발시간 : 20230321
 * @param {*} airlineId     항공사 아이디
 */
function getFlightSchedule(depAirportId,arrAirportId,depPlandTime,airlineId ){
    let url = "http://apis.data.go.kr/1613000/DmstcFlightNvgInfoService/getFlightOpratInfoList";
    let queryParams = "?serviceKey="  + encodeURIComponent(SERVICE_KEY);
    queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1'); /**/
    queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('10'); /**/
    queryParams += '&' + encodeURIComponent('_type') + '=' + encodeURIComponent('json'); /**/
    queryParams += '&' + encodeURIComponent('depAirportId') + '=' + encodeURIComponent(depAirportId); /**/
    queryParams += '&' + encodeURIComponent('arrAirportId') + '=' + encodeURIComponent(arrAirportId); /**/
    queryParams += '&' + encodeURIComponent('depPlandTime') + '=' + encodeURIComponent(depPlandTime); /**/
    queryParams += '&' + encodeURIComponent('airlineId') + '=' + encodeURIComponent(airlineId); /**/
    url = url + queryParams;
    console.log(url);

    const promise = fetch(url,{method:'GET'})
        .then((response)=>{
            return response.json();
        }).then((json)=>{
            const scheduleList = [];
            const items = json.response.body.items.item;

            if(items !=null){
                for(let i=0; i<items.length; i++ ){
                    const airlineItem = {
                        airlineNm : items[i].airlineNm,
                        arrAirportNm : items[i].arrAirportNm,
                        arrPlandTime : items[i].arrPlandTime,
                        depAirportNm : items[i].depAirportNm,
                        depPlandTime : items[i].depPlandTime,
                        economyCharge : items[i].economyCharge == undefined ? "" : items[i].economyCharge,
                        prestigeCharge : items[i].prestigeCharge == undefined ? "" : items[i].prestigeCharge,
                        vihicleId : items[i].vihicleId
                    };
                    scheduleList.push(airlineItem);
                }
                return scheduleList;
            }else{
                return [];
            }
            
        });
    return promise;
}

//항공사 목록구하기
let airlineList = [];

getAirlineList().then(function(response){
    airlineList = response;
    for(let i=0; i<airlineList.length; i++){
        console.log("항공사:" + JSON.stringify(airlineList[i]));
    }
});

window.addEventListener("DOMContentLoaded",function(){
    'use strict';
    //출발공항
    const departureId = document.getElementById("departureId");
    //도착공항
    //const arrivalId = doucment.getElementById("arrivalId");
    //비행날짜
    const plandDate = document.getElementById("plandDate");
    plandDate.value = new Date().toISOString().substring(0,10);

    //공항 정보 구하기
    const airPortPromise = getAirportList();
    airPortPromise.then((items)=>{
        const departureId = document.getElementById("departureId");
        const arrivalId = document.getElementById("arrivalId");

        for(let i=0; i<items.length; i++ ){
            const option = document.createElement("option");
            option.value=items[i].airportId;
            option.text=items[i].airportNm;    
            arrivalId.append(option.cloneNode(true));
            departureId.append(option);
        }

    });

    const validateForm = function(form){
        const departureId = form["departureId"];
        const arrivalId = form["arrivalId"];
        const departureIdValue  = departureId.options[departureId.selectedIndex].value;
        const arrivalIdValue  = arrivalId.options[arrivalId.selectedIndex].value;
        console.log("departureIdValue:" + departureIdValue);
        console.log("arrivalIdValue:" +  arrivalIdValue);
        if(departureIdValue == arrivalIdValue){
            alert("[출발]공항 , [도착]공항 다르게 선택해주세요!");
            return false;
        }
        return true;
    };

    const findForm = document.getElementById("find-form");
    
    findForm.addEventListener("submit",function(event){
        event.preventDefault();
        if( validateForm(event.target)){

            //조회로직 실행
            const  depPlandTime = plandDate.value.replaceAll("-","");
            const promiseList = [];
            for(let i=0; i<airlineList.length; i++){
                const promise = getFlightSchedule(departureId.value,arrivalId.value,depPlandTime, airlineList[i].airlineId);
                promiseList.push(promise);
            }
            const scheduleTbl = document.getElementById("schedule-tbl");
            const tbody = scheduleTbl.getElementsByTagName("tbody")[0];
            
            while(tbody.firstChild){
                tbody.firstChild.remove();
            }

            console.log("tbody ",tbody);
            Promise.all(promiseList).then((list)=>{
                const items = [];
                for(let i=0; i<list.length; i++){
                    const inlist = list[i];
                    for(let j=0; j<inlist.length; j++){
                        items.push(inlist[j]);
                    }
                }

                for(let i=0; i<items.length; i++){
                    const tr = document.createElement("tr");
                    const td1 = document.createElement("td");
                    const td2 = document.createElement("td");
                    const td3 = document.createElement("td");
                    const td4 = document.createElement("td");
                    const td5 = document.createElement("td");
                    const td6 = document.createElement("td");
                    const td7 = document.createElement("td");
                    const td8 = document.createElement("td");
                    console.log(JSON.stringify(items[i]));

                    //항공편명	vihicleId
                    td1.innerText=items[i].vihicleId;
                    //항공사명	airlineNm
                    td2.innerHTML=items[i].airlineNm;
                    //출발시간	depPlandTime
                    td3.innerText=convertDate(items[i].depPlandTime);
                    //도착시간	arrPlandTime
                    td4.innerText=convertDate(items[i].arrPlandTime);
                    
                    //일반석운임	economyCharge
                    https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Intl/NumberFormat
                    td5.innerText=new Intl.NumberFormat().format(items[i].economyCharge);
                    //비즈니스석운임	prestigeCharge
                    td6.innerText=new Intl.NumberFormat().format(items[i].prestigeCharge);
                    //출발공항	depAirportNm
                    td7.innerText=items[i].depAirportNm;
                    //  도착공항	arrAirportNm
                    td8.innerText=items[i].arrAirportNm;

                    tr.append(td1);
                    tr.append(td2);
                    tr.append(td3);
                    tr.append(td4);
                    tr.append(td5);
                    tr.append(td6);
                    tr.append(td7);
                    tr.append(td8);
                    console.log(tbody);
                    tbody.append(tr);
                }
            });

        }
    });
});

function convertDate(str){
    str = str.toString();
    //202303221125 -> 2023 03 22 11:25
    return str.substring(0,4) 
            + "-" + str.substring(4,6)
            + "-" + str.substring(6,8) 
            + " " + str.substring(8,10) 
            + ":" + str.substring(10,12);
}