import { FC } from "react";

import HistoryTable from "../components/log-history-table";
import { useQuery } from "@tanstack/react-query";
import { HistoryRecord } from "../lib/definitions";

const History: FC = () => {
  const { isPending, error, data } = useQuery<HistoryRecord[], Error>({
    queryKey: ["repoData"],
    queryFn: async () => {
      const response = await fetch("http://localhost:8080/history");
      return await response.json();
    },
  });
  if (isPending) {
    return <div>Loading...</div>;
  }
  if (error) {
    return <div>Error: {error.message}</div>;
  }

  return (
    <div>
      <h1>History Under construction</h1>
      <HistoryTable data={data} />
    </div>
  );
};
export default History;
