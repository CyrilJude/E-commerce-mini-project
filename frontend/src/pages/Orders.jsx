import { useEffect, useState } from "react";
import api from "../services/api";
export default function Orders() {
  const [o, setO] = useState([]);
  useEffect(() => {
    api.get("/orders").then((r) => setO(r.data));
  }, []);
  return (
    <main>
      <h1>My Orders</h1>
      {!o.length ? (
        <p>No orders yet.</p>
      ) : (
        <div className="orders">
          {o.map((x) => (
            <article className="order" key={x.id}>
              <div>
                <b>Order #{x.id}</b>
                <p>{new Date(x.createdAt).toLocaleString()}</p>
              </div>
              <div>
                <span className="badge">{x.status}</span>
                <span className="badge">{x.paymentStatus}</span>
              </div>
              <strong>₹{x.totalAmount.toLocaleString("en-IN")}</strong>
              <ul>
                {x.items.map((i) => (
                  <li key={i.id}>
                    {i.product.name} × {i.quantity}
                  </li>
                ))}
              </ul>
            </article>
          ))}
        </div>
      )}
    </main>
  );
}
