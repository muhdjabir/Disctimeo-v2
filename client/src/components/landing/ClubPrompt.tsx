const ClubPrompt = () => {
    return (
        <div className="w-full py-16 px-4">
            <div className="max-w-[1240px] mx-auto grid md:grid-cols-2">
                <div className="flex flex-col justify-center gap-10">
                    <h1 className="md:text-4xl sm:text-3xl text-2xl font-poppins py-2 text-left">
                        Register Your Club
                    </h1>
                    <p className="text-2xl font-montserrat text-left font-normal">
                        Create an official profile on Disctimeo to expedite and
                        display future club events and open trials
                    </p>
                    <div className="flex flex-row gap-10">
                        <button className="text-xl font-montserrat text-center underline">
                            <a href="/api/auth/login">Register your club</a>
                        </button>
                    </div>
                </div>
                <div className="align-">
                    <img
                        className="w-[500px] mx-auto my-4"
                        src="team.jpg"
                        alt="/"
                    />
                </div>
            </div>
        </div>
    );
};

export default ClubPrompt;