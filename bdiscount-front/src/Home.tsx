import axios from "axios";
import { useEffect, useState } from "react";
import { Login } from "./Login";

export function Home(props: { url: string }) {
  const [token, setToken] = useState<string | null>(null);
  const [list, setList] = useState<any[]>([]);
  const [itemToAdd, setItemToAdd] = useState<string>("");

  useEffect(() => {
    if (token) {
      fetchList(token);
    }
  }, [token]);

  function login(form: { email: string; password: string }) {
    axios.post(props.url + "login", form).then((res) => {
      console.log(res);
      setToken(res.data.token);
    });
  }

  function fetchList(token: string) {
    axios
      .post(`http://localhost:31380/items/auth/token`, {
        token,
      })
      .then((res) => {
        setList(res.data.items ? res.data.items : []);
        console.log(res);
      });
  }

  return (
    <>
      <div>
        {token ? (
          <>
            <button onClick={() => setToken("")}> Disconnect </button>
            <div>
              <input
                type="text"
                onChange={(e) => setItemToAdd(e.target.value)}
                placeholder="Item to add"
              />
              <button
                onClick={() => {
                  axios
                    .post(`http://localhost:31380/items/bdd/postItems`, {
                      token,
                      items: [{ name: `${itemToAdd}` }],
                    })
                    .then((res) => {
                      setList(res.data.items ? res.data.items : []);
                      console.log(res);
                    });
                }}
              >
                Add
              </button>
              <h1>Your List</h1>
              <ul>
                {list.map((item) => (
                  <li key={item}>{item.name}</li>
                ))}
              </ul>
            </div>
          </>
        ) : (
          <button onClick={() => (window.location.href = "/register")}>
            {" "}
            Register{" "}
          </button>
        )}
      </div>
      {!token ? <Login login={login} /> : <></>}
    </>
  );
}
