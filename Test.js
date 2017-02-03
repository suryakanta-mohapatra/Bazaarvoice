/**
 * Created by suryakanta on 2/2/17.
 */
import test from 'ava';

let java = require("java");
java.classpath.push("class");
java.classpath.push("gson-2.8.0.jar");
let Main = java.import('com.bazaarvoice.Main');
let main;

test.beforeEach(t => {
    main = new Main();
});



test.serial('Test case 1 ',  t => {
    let res = java.callMethodSync(main, "searchResults", "text", "sample");
    t.true((res===3),"ERROR!!!");
});


test.serial('Test case 2 ',  t => {
    let res = java.callMethodSync(main, "searchResults", "text", "hello");
    t.true((res===0),"ERROR!!!");
});


test.serial('Test case 3 ', t => {
    let res = java.callMethodSync(main, "searchResults", "word", "green");
    t.true((res===2),"ERROR!!!");
});


test.serial('Test case 4 ', t => {
    let res = java.callMethodSync(main, "searchResults", "word", "yellow");
    t.true((res===0),"ERROR!!!");
});


test.serial('Test case 5 ', t => {
    let res = java.callMethodSync(main, "searchResults", "id", "102");
    t.true((res===1),"ERROR!!!");
});


test.serial('Test case 6 ', t => {
    let res = java.callMethodSync(main, "searchResults", "text", "TEXT");
    t.true((res===0),"ERROR!!!");
});


test.serial('Test case 7 ', t => {
    let res = java.callMethodSync(main, "searchResults", "", "TEXT");
    t.true(((res+1)==0),"ERROR!!!");
});


test.serial('Test case 8 ', t => {
    let res = java.callMethodSync(main, "searchResults", "%^&", "TEXT");
    t.true((res==0),"ERROR!!!");
});