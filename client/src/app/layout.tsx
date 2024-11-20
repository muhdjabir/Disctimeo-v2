import NavigationBar from "@/components/navigation/NavigationBar";
import "./globals.css";
import { UserProvider } from "@auth0/nextjs-auth0/client";

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {

  const pages = [
    {
      page: "Home",
      path: "/home"
    },
    {
      page: "Clubs",
      path: "/clubs"
    },
    {
      page: "Events",
      path: "/events"
    }
  ]

  return (
    <html lang="en">
      <body>
        <UserProvider>
          <NavigationBar pages={pages} />
          <main className="pt-14">
            {children}
          </main>
        </UserProvider>
      </body>
    </html>
  );
}