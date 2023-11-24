console.log("Proxy");

const obj = {
  name: "연민호",
  age: 20,
};

obj.name = "수정";
console.log(obj.name);

const proxy = new Proxy(obj, {
  get: function (target, key) {
    console.log("조회 key 값:", key);
    if (!target[key]) throw new Error(`존재하지 않는 속성(${key})`);
    return target[key];
  },
  set: function (target, key, value) {
    console.log(`${key}의 값을 ${value}(으)로 변경`);
    target[key] = value;
  },
});

console.log(proxy.name);
// console.log(proxy.id); //에러발생
proxy.name = "안녕";
console.log(obj);
