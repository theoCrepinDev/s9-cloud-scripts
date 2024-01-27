import axios from "axios";
import { useState } from "react";
import { Login } from "./Login";

export function Home(props: {url: string}) {
  const [token, setToken] = useState<string | null>(null)

  
    function login(form: { email: string; password: string }) {
        axios.post('http://' + props.url + 'login', form)
          .then((res) => {
            console.log(res)
            setToken(res.data.token)
          })
      }
    return (
        <>
        <div>
            {token ? 
                <button onClick={() => setToken('')}> Disconnect </button>
                : 
                <button onClick={() => window.location.href = '/register'}> Register </button>
                }
        </div>
        {!token ?(<Login login={login} />
              ) : (
                <> 
                </>)
        }
        </>
    )
}