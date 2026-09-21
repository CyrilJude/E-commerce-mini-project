import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import api from "../services/api";
export default function Cart() {
  const [i, setI] = useState([]),
    nav = useNavigate(),
    load = () => api.get("/cart").then((r) => setI(r.data));
  useEffect(() => {
    load();
  }, []);
  async function update(x, q) {
    if (q < 1) return;
    await api.put("/cart/items/" + x.product.id + "?quantity=" + q);
    load();
  }
  async function remove(x) {
    await api.delete("/cart/items/" + x.product.id);
    load();
  }
  const total = i.reduce((s, x) => s + x.product.price * x.quantity, 0);
  return (
    <main>
      <h1>Your Cart</h1>
      {!i.length ? (
        <div className="empty">
          Your cart is empty. <Link to="/">Browse products</Link>
        </div>
      ) : (
        <>
          <div className="cartList">
            {i.map((x) => (
              <div className="cartItem" key={x.id}>
                <img src={x.product.imageUrl} />
                <div className="grow">
                  <h3>{x.product.name}</h3>
                  <p>₹{x.product.price.toLocaleString("en-IN")}</p>
                </div>
                <div className="qty">
                  <button onClick={() => update(x, x.quantity - 1)}>-</button>
                  <b>{x.quantity}</b>
                  <button onClick={() => update(x, x.quantity + 1)}>+</button>
                </div>
                <button className="danger" onClick={() => remove(x)}>
                  Remove
                </button>
              </div>
            ))}
          </div>
          <div className="summary">
            <h2>Total: ₹{total.toLocaleString("en-IN")}</h2>
            <button className="primary" onClick={() => nav("/checkout")}>
              Proceed to checkout
            </button>
          </div>
        </>
      )}
    </main>
  );
}
