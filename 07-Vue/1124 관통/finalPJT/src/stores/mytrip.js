import { ref } from 'vue';
import { defineStore } from 'pinia';
import axios from '@/utils/boardAxios';

export const useMyTripStore = defineStore('mytrip', () => {
  /* ======나의 모든 여행 계획 보기 Start============== */
  const plans = ref([]);
  const getPlans = async () => {
    const { data } = await axios.get(`/api/v1/plans`);
    console.log('나의 여행 목록 데이터', data);
    plans.value = data.result;
  };
  /* ======나의 모든 여행 계획 보기 End============== */

  /* ======나의 여행계획 상세보기 Start============== */
  const plan = ref({});
  const totalPlans = ref([]);
  const getPlan = async (planId) => {
    const { data } = await axios.get(`/api/v1/plans/${planId}`);
    console.log('getPlan의 응답 데이터 : ', data);

    plan.value = data.result;
    totalPlans.value = plan.value.dayPlans;
  };
  /* ======나의 여행계획 상세보기 End============== */

  /* ======여행 날짜 얻기 Start============== */
  const dateRange = ref([]); //여행 시작날짜부터 종료날짜까지 들어있음
  const calculateDateDifference = (start, end) => {
    if (start && end) {
      const startDateArray = start.split('-').map(Number);
      const endDateArray = end.split('-').map(Number);
      const startDateObj = new Date(
        startDateArray[0],
        startDateArray[1] - 1,
        startDateArray[2]
      );
      const endDateObj = new Date(
        endDateArray[0],
        endDateArray[1] - 1,
        endDateArray[2]
      );

      const dateList = [];
      for (
        let date = new Date(startDateObj);
        date <= endDateObj;
        date.setDate(date.getDate() + 1)
      ) {
        const formattedDate = date.toLocaleDateString('ko-KR', {
          month: 'long',
          day: 'numeric',
        });
        dateList.push(formattedDate);
      }

      dateRange.value = dateList;
      console.log('dateRange:', dateRange.value);

      const differenceDays = dateList.length;
      return differenceDays;
    } else {
      return '날짜를 선택하세요.';
    }
  };

  /* ======여행 날짜 얻기 End============== */

  /* ======나의 여행 계획 프로젝트 등록하기 Start================ */
  const makeNewPlan = async (mytrip) => {
    console.log('makeNewPlan() 요청, 등록데이터 : ', mytrip);
    return await axios.post(`/api/v1/plans/`, mytrip);
  };
  /* ======나의 여행 계획 프로젝트 등록하기 End================ */

  /* ======나의 여행 계획 프로젝트 삭제하기 Start================ */
  const deletePlan = async (planId) => {
    console.log('deletePlan() 요청, 삭제할 플랜 : ', planId);
    return await axios.delete(`/api/v1/plans/${planId}`);
  };
  /* ======나의 여행 계획 프로젝트 삭제하기 End================ */

  /* ======나의 여행계획 수정하기 Start================ */
  const modifyTrip = async (planId, mytrip) => {
    console.log('modifyTrip() 요청, 변경할 여행 : ', planId);
    return await axios.put(`/api/v1/plans/${planId}`, mytrip);
  };
  /* ======나의 여행계획 수정하기 End================ */

  /* ======나의 여행계획에 장소 등록하기 Start================ */
  const registNewPlace = async (planId, newlocation) => {
    console.log('registNewPlace() 요청, 등록할 장소 : ', newlocation);
    return await axios.post(`/api/v1/plans/${planId}/dayPlans`, newlocation);
  };
  /* ======나의 여행계획에 장소 등록하기 End================ */

  /* ======나의 여행계획 장소 수정하기 Start================ */
  const modifyPlan = async (planId, modifyPlans) => {
    console.log('modifyPlan() 요청, 수정할 계획 : ', modifyPlans);
    return await axios.put(`/api/v1/plans/${planId}/dayPlans`, modifyPlans);
  };
  /* ======나의 여행계획 장소 수정하기 End================ */

  /* ======나의 여행계획 하루 플랜 장소 단건 삭제하기 Start================ */
  const deleteDayPlan = async (planId, dayId) => {
    console.log('deleteDayPlan() 요청, 삭제할 아이디 : ', dayId);
    return await axios.delete(`/api/v1/plans/${planId}/dayPlans/${dayId}`);
  };
  /* ======나의 여행계획 하루 플랜 장소 단건 삭제하기 End================ */

  return {
    plans,
    getPlans,
    makeNewPlan,
    plan,
    totalPlans,
    getPlan,
    dateRange,
    calculateDateDifference,
    deletePlan,
    modifyTrip,
    registNewPlace,
    modifyPlan,
    deleteDayPlan,
  };
});
