import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import api from "../services/api";
import { useAuth } from "../context/AuthContext";
import { AuthForm } from "./Login";
export default function Register() {
  const [f, setF] = useState({
      name: "",
      email: "",
      password: "",
      phone: "",
      address: "",
    }),
    [e, setE] = useState(""),
    { save } = useAuth(),
    nav = useNavigate();
  async function submit(x) {
    x.preventDefault();
    try {
      save((await api.post("/auth/register", f)).data);
      nav("/");
    } catch (x) {
      setE(x.response?.data?.message || "Registration failed");
    }
  }
  return (
    <AuthForm
      title="Create your account"
      subtitle="Start shopping in minutes"
      register
      fields={f}
      setFields={setF}
      onSubmit={submit}
      error={e}
      button="Register"
      footer={
        <>
          Already have an account? <Link to="/login">Login</Link>
        </>
      }
    />
  );
}
